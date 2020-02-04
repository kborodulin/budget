package ru.innopolis.service;

import ru.innopolis.domain.Currency;

import java.util.List;

public interface CurrencyService {
    void save(Currency currency);

    Currency findById(Long id);

    void delete(Currency currency);

    List<Currency> findAll();
}