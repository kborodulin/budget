package ru.innopolis.service;

import ru.innopolis.domain.Operation;

import java.util.List;

public interface OperationService {
    void save(Operation operation);

    Operation findById(Long id);

    void delete(Operation operation);

    List<Operation> findAll();

    List<Operation> allIncomeUser(Long userid);

    List<Operation> allExpensesUser(Long userid);
}