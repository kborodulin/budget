package ru.innopolis.service;

import ru.innopolis.domain.Operation;

import java.time.LocalDate;
import java.util.List;

public interface OperationService {
    void save(Operation operation);

    Operation findById(Long id);

    void delete(Operation operation);

    List<Operation> findAll();

    List<Object[]> allIncomeUser(Long userid, LocalDate startDate, LocalDate endDate);

    List<Operation> allExpensesUser(Long famemId, LocalDate startDate, LocalDate endDate);

    void clearDelete(Operation operation);
}