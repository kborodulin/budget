package ru.innopolis.service;

import ru.innopolis.domain.Account;

import java.util.List;
import java.util.Map;

public interface AccountService {
    void save(Account account);

    Account findById(Long id);

    void delete(Account account);

    List<Account> findAll();

    List<Account> findByFamemid(Long famemid);

    Map<Long, Double> balance(Long userid);
}
