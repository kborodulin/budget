package ru.innopolis.service;

import ru.innopolis.domain.AccountType;

import java.util.List;

public interface AccountTypeService {
    void save(AccountType accountType);

    AccountType findById(Long id);

    void delete(AccountType accountType);

    List<AccountType> findAll();
}