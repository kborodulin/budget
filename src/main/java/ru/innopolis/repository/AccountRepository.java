package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.domain.Account;
import ru.innopolis.domain.Famem;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByFamem(Famem famem);

    @Query("SELECT SUM (a.amount) " +
            "FROM Famem f " +
            "JOIN f.accountList a " +
            "WHERE f.famemid = ?1 ")
    BigDecimal famemBalance(Long famemId);
}