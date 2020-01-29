package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Account;
import ru.innopolis.domain.TypeOperation;
import ru.innopolis.repository.AccountRepository;
import ru.innopolis.repository.TypeOperationRepository;
import ru.innopolis.service.AccountService;
import ru.innopolis.service.TypeOperationService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@Slf4j
public class TypeOperationImpl implements TypeOperationService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TypeOperationRepository typeOperationRepository;

    @Override
    public void save(TypeOperation typeOperation) {
        log.info("saved typeOperation to DB {}", typeOperation);
        typeOperationRepository.save(typeOperation);
    }

    @Override
    public TypeOperation findById(Long id) {
        log.info("find typeOperation by ID {}", id);
        return typeOperationRepository.findById(id).get();
    }

    @Override
    public void delete(TypeOperation typeOperation) {
        log.info("delete typeOperation from DB {}", typeOperation);
        typeOperationRepository.delete(typeOperation);
    }

    @Override
    public List<TypeOperation> findAll() {
        log.info("find all typeOperation");
        return typeOperationRepository.findAll();
    }
}