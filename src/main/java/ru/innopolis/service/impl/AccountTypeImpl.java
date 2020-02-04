package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.AccountType;
import ru.innopolis.repository.AccountTypeRepository;
import ru.innopolis.service.AccountTypeService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AccountTypeImpl implements AccountTypeService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public void save(AccountType accountType) {
        log.info("saved accounttype to DB {}", accountType);
        accountTypeRepository.save(accountType);
    }

    @Override
    public AccountType findById(Long id) {
        log.info("find accounttype by ID {}", id);
        return accountTypeRepository.findById(id).get();
    }

    @Override
    public void delete(AccountType accountType) {
        log.info("delete accounttype from DB {}", accountType);
        accountTypeRepository.delete(accountType);
    }

    @Override
    public List<AccountType> findAll() {
        log.info("find all accounttype");
        return accountTypeRepository.findAll();
    }
}