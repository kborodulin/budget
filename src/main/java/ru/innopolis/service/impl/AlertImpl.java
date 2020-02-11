package ru.innopolis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.Alert;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.Family;
import ru.innopolis.domain.User;
import ru.innopolis.repository.AlertRepository;
import ru.innopolis.service.AlertService;
import ru.innopolis.service.FamemService;
import ru.innopolis.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private FamemService famemService;

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
    public void setAlert(User receiver, User initiator) {
        log.info("set new alert for user with email {} from user with email {}", receiver.getEmail(), initiator.getEmail());
        Alert alert = new Alert();
        alert.setInitiator(initiator.getFamem());
        alert.setReceiver(receiver.getFamem());
        alert.setDatealert(LocalDateTime.now());
        alert.setFamily(initiator.getFamem().getFamily());
        alert.setIsalertsignproc(BigDecimal.ZERO);
        save(alert);
    }

    @Override
    public Alert findByReceiver(User user) {
        log.info("find alert by receiver {}", user);
        return alertRepository.findFirstByReceiver(user.getFamem());
    }

    @Override
    public Alert checkForAlerts(Famem famem) {
        famem = famemService.findById(famem.getFamemid());
        if (famem.getAlertListForReceiver()==null) return null;
        Optional<Alert> alert = famem.getAlertListForReceiver().stream()
                .filter(a -> !a.isAlertSignProc())
                .findFirst();
        return alert.orElse(null);
    }

    @Override
    public void deleteByFamily(Family family) {
        alertRepository.deleteAllByFamily(family);
    }

    @Override
    public List<Alert> findByInitiator(Long famemId, BigDecimal proc) {
        return alertRepository.findAlertsByInitiator(famemId, proc);
    }
}