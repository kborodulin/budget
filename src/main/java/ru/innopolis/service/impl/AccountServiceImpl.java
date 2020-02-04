package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Account;
import ru.innopolis.repository.AccountRepository;
import ru.innopolis.service.AccountService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void save(Account account) {
        log.info("saved account to DB {}", account);
        accountRepository.save(account);
    }

    @Override
    public Account findById(Long id) {
        log.info("find account by ID {}", id);
        return accountRepository.findById(id).get();
    }

    @Override
    public void delete(Account account) {
        log.info("delete account from DB {}", account);
        accountRepository.delete(account);
    }

    @Override
    public List<Account> findAll() {
        log.info("find all account");
        return accountRepository.findAll();
    }
}