package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.domain.Operation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query("SELECT o FROM Operation o JOIN o.account a " +
            "WHERE a.famem.famemid = ?1 " +
            "AND o.typeoperationid=2 " +
            "AND o.dateoper BETWEEN ?2 AND ?3 " +
            "AND o.category.categoryid = (case when ?4 = 0 then o.category.categoryid else ?4 end) " +
            "order by o.datewritedb desc")
    List<Operation> findUserExpensesInPeriod(Long famemId, LocalDate startDate, LocalDate endDate, int categoryid);
}