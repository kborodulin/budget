package ru.innopolis.service;

import ru.innopolis.domain.Alert;

import java.util.List;

public interface AlertService {
    void save(Alert alert);

    Alert findById(Long id);

    void delete(Alert alert);

    List<Alert> findAll();
}
