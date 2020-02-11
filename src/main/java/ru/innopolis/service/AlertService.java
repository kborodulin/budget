package ru.innopolis.service;

import ru.innopolis.domain.Alert;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.Family;
import ru.innopolis.domain.User;

import java.math.BigDecimal;
import java.util.List;

public interface AlertService {
    void save(Alert alert);

    Alert findById(Long id);

    void delete(Alert alert);

    List<Alert> findAll();

    void setAlert(User receiver, User initiator);

    Alert findByReceiver(User user);

    Alert checkForAlerts(Famem famem);

    void deleteByFamily(Family family);

    List<Alert> findByInitiator(Long famemId, BigDecimal proc);
}
