package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Operation;
import ru.innopolis.repository.OperationRepository;
import ru.innopolis.service.OperationService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@Transactional
@Slf4j
public class OperationServiceImpl implements OperationService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private OperationRepository operationRepository;

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
    public List<Operation> allIncomeUser(Long userid) {
        Query query = em.createNativeQuery("select " +
                "o.operationid, " +
                "o.typeoperationid, " +
                "o.categoryid, " +
                "o.accountid, " +
                "o.amount, " +
                "o.dateoper, " +
                "o.datewritedb,o.comment \n" +
                "from operation o \n" +
                "join account a on a.accountid = o.accountid \n" +
                "join famem f on f.famemid = a.famemid \n" +
                "where o.typeoperationid = 1 and f.userid = ?");
        query.setParameter(1, userid);
        List<Operation> operations = query.getResultList();
        return operations;
    }

    @Override
    public List<Operation> allExpensesUser(Long userid) {
        Query query = em.createNativeQuery("select " +
                "o.operationid, " +
                "o.typeoperationid, " +
                "o.categoryid, " +
                "o.accountid, " +
                "o.amount, " +
                "o.dateoper, " +
                "o.datewritedb,o.comment \n" +
                "from operation o \n" +
                "join account a on a.accountid = o.accountid \n" +
                "join famem f on f.famemid = a.famemid \n" +
                "where o.typeoperationid = 2 and f.userid = ?");
        query.setParameter(1, userid);
        List<Operation> operations = query.getResultList();
        return operations;
    }
}