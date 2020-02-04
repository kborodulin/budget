package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Currency;
import ru.innopolis.repository.CurrencyRepository;
import ru.innopolis.service.CurrencyService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public void save(Currency currency) {
        log.info("saved currency to DB {}", currency);
        currencyRepository.save(currency);
    }

    @Override
    public Currency findById(Long id) {
        log.info("find currency by ID {}", id);
        return currencyRepository.findById(id).get();
    }

    @Override
    public void delete(Currency currency) {
        log.info("delete currency from DB {}", currency);
        currencyRepository.delete(currency);
    }

    @Override
    public List<Currency> findAll() {
        log.info("find all currency");
        return currencyRepository.findAll();
    }
}