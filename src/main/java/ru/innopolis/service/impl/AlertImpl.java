package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Alert;
import ru.innopolis.repository.AlertRepository;
import ru.innopolis.service.AlertService;
import ru.innopolis.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AlertImpl implements AlertService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserService userService;

    @Autowired
    private AlertRepository alertRepository;

    @Override
    public void save(Alert alert) {
        log.info("saved alert to DB {}", alert);
        alertRepository.save(alert);
    }

    @Override
    public Alert findById(Long id) {
        log.info("find alert by ID {}", id);
        return alertRepository.findById(id).get();
    }

    @Override
    public void delete(Alert accountType) {
        log.info("delete alert from DB {}", accountType);
        alertRepository.delete(accountType);
    }

    @Override
    public List<Alert> findAll() {
        log.info("find all alert");
        return alertRepository.findAll();
    }

    @Override
    public void setAlert(String email, Long userId) {
        log.info("set new alert for user with email {} from user with id {}", email, userId);
        Alert alert = new Alert();
        alert.setInitiator(userId);
        alert.setReceiver(userService.findFirstByEmail(email).getUserid());
        alert.setDatealert(LocalDateTime.now());
        save(alert);
    }
}