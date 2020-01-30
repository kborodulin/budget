package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Operation;
import ru.innopolis.repository.OperationRepository;
import ru.innopolis.service.OperationService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}