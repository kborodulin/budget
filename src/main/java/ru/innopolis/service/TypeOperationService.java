package ru.innopolis.service;

import ru.innopolis.domain.TypeOperation;

import java.util.List;

public interface TypeOperationService {
    void save(TypeOperation typeOperation);

    TypeOperation findById(Long id);

    void delete(TypeOperation typeOperation);

    List<TypeOperation> findAll();
}