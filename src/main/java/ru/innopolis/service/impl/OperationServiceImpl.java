package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Account;
import ru.innopolis.domain.Operation;
import ru.innopolis.repository.OperationRepository;
import ru.innopolis.service.AccountService;
import ru.innopolis.service.OperationService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

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
        return operationRepository.findById(id).get();
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
    public List<Object[]> allIncomeUser(Long userid, LocalDate startDate, LocalDate endDate) {
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
                "order by o.operationid desc\n");
        query.setParameter(1, userid);
        query.setParameter(2, startDate);
        query.setParameter(3, endDate);
        List<Object[]> objects = query.getResultList();
        return objects;
    }

    @Override
    public List<Operation> allExpensesUser(Long famemId, LocalDate startDate, LocalDate endDate) {
        return operationRepository.findUserExpensesInPeriod(famemId, startDate, endDate);
    }

    @Override
    public void clearDelete(Operation operation) {
        Account account = accountService.findById(operation.getAccount().getAccountid());
        account.setAmount(account.getAmount().add(operation.getAmount()));
        delete(operation);
    }
}