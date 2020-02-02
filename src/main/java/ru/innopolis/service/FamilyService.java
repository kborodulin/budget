package ru.innopolis.service;

import ru.innopolis.domain.Family;

import java.util.List;

public interface FamilyService {
    void save(Family family);

    Family findById(Long id);

    void delete(Family family);

    List<Family> findAll();

    void create(Family family, String login);

    Family findByName(String name);
}
