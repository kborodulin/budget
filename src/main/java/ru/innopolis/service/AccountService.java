package ru.innopolis.service;

import ru.innopolis.domain.Account;

import java.util.List;

public interface AccountService {
    void save(Account account);

    Account findById(Long id);

    void delete(Account account);

    List<Account> findAll();
}
