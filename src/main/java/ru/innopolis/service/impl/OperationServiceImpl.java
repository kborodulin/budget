package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.*;
import ru.innopolis.repository.OperationRepository;
import ru.innopolis.service.AccountService;
import ru.innopolis.service.OperationService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ru.innopolis.controller.IncomeController.MAX_COUNT_ELEMENT_PAGE;

@Service
@Transactional
@Slf4j
public class OperationServiceImpl implements OperationService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public void save(Operation operation) {
        log.info("saved operation to DB {}", operation);
        operationRepository.save(operation);
    }

    @Override
    public Operation findById(Long id) {
        log.info("find operation by ID {}", id);
        Operation operation = null;
        Boolean present = operationRepository.findById(id).isPresent();
        if (present) {
            operation = operationRepository.findById(id).get();
        }
        return operation;
    }

    @Override
    public void delete(Operation operation) {
        log.info("delete operation from DB {}", operation);
        operationRepository.delete(operation);
    }

    @Override
    public List<Operation> findAll() {
        log.info("find all operation");
        return operationRepository.findAll();
    }

    @Override
    public List<Object[]> allIncomeUser(Long userid, LocalDate startDate, LocalDate endDate, int categoryid, Integer page) {
        Query query = em.createNativeQuery("select\n" +
                "  a.name,\n" +
                "  coalesce(o.amount, 0) amount,\n" +
                "  o.dateoper,\n" +
                "  c.name as categoryname,\n" +
                "  o.comment,\n" +
                "  o.operationid\n" +
                "from operation o\n" +
                "join typeoperation typ on typ.typeoperationid = o.typeoperationid\n" +
                "join category c on c.categoryid = o.categoryid\n" +
                "join account a on a.accountid = o.accountid\n" +
                "join famem f on f.famemid = a.famemid \n" +
                "where typ.typeoperationid = 1 \n" +
                "and f.userid = ? \n" +
                "and o.dateoper between ? and ? \n" +
                "and c.categoryid = (case when ? = 0 then c.categoryid else ? end) \n" +
                "order by o.operationid desc\n");
        query.setParameter(1, userid);
        query.setParameter(2, startDate);
        query.setParameter(3, endDate);
        query.setParameter(4, categoryid);
        query.setParameter(5, categoryid);
        List<Object[]> objects = null;
        if (page == null) {
            objects = query.getResultList();
        } else {
            objects = query.setFirstResult((page - 1) * MAX_COUNT_ELEMENT_PAGE).setMaxResults(MAX_COUNT_ELEMENT_PAGE).getResultList();
        }
        return objects;
    }


    @Override
    public List<Object[]> findAllTransactionsByPeriod(Long familyid, LocalDate startDate, LocalDate endDate, Integer page) {
        Query query = em.createNativeQuery("select\n" +
                "  a.name,\n" +
                "  coalesce(o.amount, 0) amount,\n" +
                "  o.dateoper,\n" +
                "  o.comment,\n" +
                "  o.operationid,\n" +
                "  typ.typeoperationid,\n" +
                "  u.login \n" +
                "from  operation o\n" +
                "join typeoperation typ on typ.typeoperationid = o.typeoperationid\n" +
                "join account a on a.accountid = o.accountid\n" +
                "join famem f on f.famemid = a.famemid \n" +
                "join users u on u.userid = f.userid\n" +
                "where (typ.typeoperationid = 3 or typ.typeoperationid = 4) \n" +
                "and f.familyid = ? \n" +
                "and o.dateoper between ? and ? \n" +
                "order by o.operationid desc\n");
        query.setParameter(1, familyid);
        query.setParameter(2, startDate);
        query.setParameter(3, endDate);
        List<Object[]> objects = null;
        if (page == null) {
            objects = query.getResultList();
        }
        else {
            objects = query.setFirstResult((page - 1) * MAX_COUNT_ELEMENT_PAGE*2).setMaxResults(MAX_COUNT_ELEMENT_PAGE*2).getResultList();
        }
        return objects;
    }


    @Override
    public List<Operation> allExpensesUser(Long famemId, LocalDate startDate, LocalDate endDate, int categoryid, Pageable page) {
        return operationRepository.findUserExpensesInPeriod(famemId, startDate, endDate, categoryid, page);
    }

    @Override
    public void clearDelete(Operation operation) {
        Account account = accountService.findById(operation.getAccount().getAccountid());
        if (operation.getTypeoperationid() == 1L) {
            account.setAmount(account.getAmount().subtract(operation.getAmount()));
        }
        if (operation.getTypeoperationid() == 2L) {
            account.setAmount(account.getAmount().add(operation.getAmount()));
        }
        delete(operation);
    }

    @Override
    public List<BigDecimal> findMembersExpenses(List<Famem> familyMembers, List<LocalDate> period) {
        List<BigDecimal> summaryMembersExp = new ArrayList<>();
        for (Famem famem : familyMembers) {
            BigDecimal summaryExp = allExpensesUser(famem.getFamemid(), period.get(0), period.get(1), 0, null)
                    .stream()
                    .map(Operation::getAmount)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO);
            summaryMembersExp.add(summaryExp);
        }
        return summaryMembersExp;
    }

    @Override
    public List<BigDecimal> findAllCategoryExpenses(Family family, List<Category> categoryList, List<LocalDate> period) {
        List<BigDecimal> summaryCategoryExp = new ArrayList<>();
        for (Category category : categoryList) {
            List<Operation> operations = findOneCategoryExpenses(family, category, period);
            BigDecimal summaryExp = operations.stream()
                    .map(Operation::getAmount)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO);
            summaryCategoryExp.add(summaryExp);
        }
        return summaryCategoryExp;
    }

    @Override
    public List<Operation> findOneCategoryExpenses(Family family, Category category, List<LocalDate> period) {
        return operationRepository.findCategoryExpensesForFamily(family.getFamilyid(), category.getCategoryid(), period.get(0), period.get(1));
    }

    @Override
    public BigDecimal getSummaryExpenses(Family family, List<LocalDate> period) {
        return operationRepository.getSummaryExpenses(family.getFamilyid(), period.get(0), period.get(1))
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal getSummaryIncome(Family family, List<LocalDate> period) {
        return operationRepository.getSummaryIncome(family.getFamilyid(), period.get(0), period.get(1))
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public List<Operation> getTopFamilyOperations(Family family, Pageable pageable) {
        return operationRepository.getTopByFamily(family.getFamilyid(), pageable);
    }


}