package ru.innopolis.service;

import org.springframework.data.domain.Pageable;
import ru.innopolis.domain.Category;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.Family;
import ru.innopolis.domain.Operation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OperationService {
    void save(Operation operation);

    Operation findById(Long id);

    void delete(Operation operation);

    List<Operation> findAll();

    List<Object[]> allIncomeUser(Long userid, LocalDate startDate, LocalDate endDate, int categoryid, Integer page);

    List<Operation> allExpensesUser(Long famemId, LocalDate startDate, LocalDate endDate, int categoryid, Pageable page);

    void clearDelete(Operation operation);

    List<BigDecimal> findMembersExpenses(List<Famem> familyMembers, List<LocalDate> period);

    List<BigDecimal> findAllCategoryExpenses(Family family, List<Category> categoryList, List<LocalDate> period);

    List<Operation> findOneCategoryExpenses(Family family, Category category, List<LocalDate> period);

    BigDecimal getSummaryExpenses(Family family, List<LocalDate> period);

    BigDecimal getSummaryIncome(Family family, List<LocalDate> period);

    List<Operation> getTopFamilyOperations(Family family, Pageable pageable);

    List<Object[]> findAllTransactionsByPeriod(Long familyid, LocalDate startDate, LocalDate endDate, Integer page, Long userid);

    List<Operation> getOperationsByFamemsAndCategories(List<Long> familyMembers, List<Long> categoryList, LocalDate startDate, LocalDate endDate, int operationType, Integer page);
}