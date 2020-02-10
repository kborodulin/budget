package ru.innopolis.service;

import ru.innopolis.domain.Famem;

import java.util.List;

public interface FamemService {
    void save(Famem famem);

    Famem findById(Long id);

    void delete(Famem famem);

    List<Famem> findAll();

    void update(Famem updatedFamem, Famem famemInfo);
}