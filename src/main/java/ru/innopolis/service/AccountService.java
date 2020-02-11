package ru.innopolis.service;

import ru.innopolis.domain.Account;

import java.util.List;
import java.util.Map;

public interface AccountService {
    void save(Account account);

    Account findById(Long id);

    void delete(Account account);

    List<Account> findAll();

    Map<Long, Double> balance(Long userid);

    List<Account> findAllByUser(Long userid);
}