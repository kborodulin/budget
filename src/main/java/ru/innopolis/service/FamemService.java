package ru.innopolis.service;

import ru.innopolis.domain.Famem;
import ru.innopolis.domain.Family;
import ru.innopolis.domain.User;

import java.util.List;

public interface FamemService {
    void save(Famem famem);

    Famem findById(Long id);

    void delete(Famem famem);

    List<Famem> findAll();

    void update(Famem updatedFamem, Famem famemInfo);

    Famem findByUser(User user);

    List<Famem> findAllByFamily(Family family);
}