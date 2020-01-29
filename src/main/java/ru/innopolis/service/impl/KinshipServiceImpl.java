package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Kinship;
import ru.innopolis.repository.KinshipRepository;
import ru.innopolis.service.KinshipService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@Slf4j
public class KinshipServiceImpl implements KinshipService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private KinshipRepository kinshipRepository;

    @Override
    public void save(Kinship kinship) {
        log.info("saved kinship to DB {}", kinship);
        kinshipRepository.save(kinship);
    }

    @Override
    public Kinship findById(Long id) {
        log.info("find kinship by ID {}", id);
        return kinshipRepository.findById(id).get();
    }

    @Override
    public void delete(Kinship kinship) {
        log.info("delete kinship from DB {}", kinship);
        kinshipRepository.delete(kinship);
    }

    @Override
    public List<Kinship> findAll() {
        log.info("find all account");
        return kinshipRepository.findAll();
    }
}