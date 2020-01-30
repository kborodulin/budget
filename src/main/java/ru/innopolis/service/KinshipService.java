package ru.innopolis.service;

import ru.innopolis.domain.Kinship;

import java.util.List;

public interface KinshipService {
    void save(Kinship kinship);

    Kinship findById(Long id);

    void delete(Kinship kinship);

    List<Kinship> findAll();
}
