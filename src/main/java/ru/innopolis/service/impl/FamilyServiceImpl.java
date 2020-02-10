package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Family;
import ru.innopolis.repository.FamilyRepository;
import ru.innopolis.service.FamemService;
import ru.innopolis.service.FamilyService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@Slf4j
public class FamilyServiceImpl implements FamilyService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private FamilyRepository familyRepository;

    private FamemService famemService;

    @Autowired
    public void setUserService(FamemService famemService) {
        this.famemService = famemService;
    }

    @Override
    public void save(Family family) {
        log.info("saved family to DB {}", family);
        familyRepository.save(family);
    }

    @Override
    public Family findById(Long id) {
        log.info("find family by ID {}", id);
        return familyRepository.findById(id).get();
    }

    @Override
    public void delete(Family family) {
        log.info("delete family from DB {}", family);
        familyRepository.delete(family);
    }

    @Override
    public List<Family> findAll() {
        log.info("find all family");
        return familyRepository.findAll();
    }

    public Family findByName(String name) {
        log.info("find family by name {}", name);
        return familyRepository.findFirstByName(name);
    }
}