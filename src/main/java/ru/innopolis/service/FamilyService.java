package ru.innopolis.service;

import ru.innopolis.domain.Family;

import java.util.List;

public interface FamilyService {
    void save(Family family);

    Family findById(Long id);

    void delete(Family family);

    List<Family> findAll();

    Family findByName(String name);
}
