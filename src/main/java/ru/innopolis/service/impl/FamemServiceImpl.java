package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Famem;
import ru.innopolis.repository.FamemRepository;
import ru.innopolis.service.FamemService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@Slf4j
public class FamemServiceImpl implements FamemService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private FamemRepository famemRepository;

    @Override
    public void save(Famem famem) {
        log.info("saved famem to DB {}", famem);
        famemRepository.save(famem);
    }

    @Override
    public Famem findById(Long id) {
        log.info("find famem by ID {}", id);
        return famemRepository.findById(id).get();
    }

    @Override
    public void delete(Famem famem) {
        log.info("delete famem from DB {}", famem);
        famemRepository.delete(famem);
    }

    @Override
    public List<Famem> findAll() {
        log.info("find all famem");
        return famemRepository.findAll();
    }
}