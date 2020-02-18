package ru.innopolis.service;

import ru.innopolis.domain.Account;
import ru.innopolis.domain.Famem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface AccountService {
    void save(Account account);

    Account findById(Long id);

    void delete(Account account);

    List<Account> findAll();

    Map<Long, Double> balance(Long userid);

    List<Account> findAllByUser(Long userid);

    List<Account> findAllByFamem(Famem famem);

    List<Account> findAllByUserSort(Long userid, Long accountid);

    BigDecimal famemBalance(Famem famem);
}