package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.domain.Alert;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.Family;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    Alert findFirstByReceiver(Famem famem);
    void deleteAllByFamily(Family family);
//
    @Query("SELECT a FROM Alert a WHERE a.initiator.famemid = ?1 and a.isalertsignproc = ?2")
    List<Alert> findAlertsByInitiator(Long famemId, BigDecimal proc);
}