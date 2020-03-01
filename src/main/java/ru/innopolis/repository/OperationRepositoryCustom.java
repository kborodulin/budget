package ru.innopolis.repository;

import org.springframework.stereotype.Repository;
import ru.innopolis.domain.Account;
import ru.innopolis.domain.Category;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.Operation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ru.innopolis.controller.IncomeController.MAX_COUNT_ELEMENT_PAGE;

@Repository
public class OperationRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Operation> findAllOperationsBy(List<Famem> familyMembers, List<Category> categoryList, LocalDate startDate, LocalDate endDate, int operationType, Integer page) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Operation> query = cb.createQuery(Operation.class);

        Root<Operation> operation = query.from(Operation.class);
        Join<Operation, Account> accountJoin = operation.join("account");

        Path<Long> famemPath = accountJoin.get("famem");
        Predicate memberPredicate = getFamilyMemberPredicate(familyMembers, cb, famemPath);

        Path<Long> categoryPath = operation.get("category");
        Predicate categoryPredicate = getCategoryPredicate(categoryList, cb, categoryPath);

        Path<LocalDate> datePath = operation.get("dateoper");
        Predicate datePredicate = getDatePredicate(startDate, endDate, cb, datePath);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(memberPredicate);
        predicates.add(categoryPredicate);
        predicates.add(datePredicate);
        predicates.add(cb.equal(operation.get("typeoperationid"), operationType));

        query.select(operation)
                .where(cb.and(predicates.toArray(new Predicate[0]))).orderBy(cb.asc(operation.get("dateoper")));
        TypedQuery<Operation> operationTypedQuery = entityManager.createQuery(query);
        if (page != null) {
            operationTypedQuery.setFirstResult(page*MAX_COUNT_ELEMENT_PAGE);
            operationTypedQuery.setMaxResults(MAX_COUNT_ELEMENT_PAGE);
        }

        return operationTypedQuery.getResultList();
    }

    private Predicate getFamilyMemberPredicate(List<Famem> familyMembers, CriteriaBuilder cb, Path<Long> famemPath) {
        List<Predicate> memberPredicates = new ArrayList<>();
        for (Famem familyMember : familyMembers) {
            memberPredicates.add(cb.equal(famemPath, familyMember));
        }
        return cb.or(memberPredicates.toArray(new Predicate[0]));
    }

    private Predicate getCategoryPredicate(List<Category> categoryList, CriteriaBuilder cb, Path<Long> categoryPath) {
        List<Predicate> categoryPredicate = new ArrayList<>();
        for (Category category : categoryList) {
            categoryPredicate.add(cb.equal(categoryPath, category));
        }
        return cb.or(categoryPredicate.toArray(new Predicate[0]));
    }

    private Predicate getDatePredicate(LocalDate startDate, LocalDate endDate, CriteriaBuilder cb, Path<LocalDate> datePath) {
        Predicate startPredicate = cb.greaterThanOrEqualTo(datePath, startDate);
        Predicate endPredicate = cb.lessThanOrEqualTo(datePath, endDate);
        return cb.and(startPredicate, endPredicate);
    }

}
