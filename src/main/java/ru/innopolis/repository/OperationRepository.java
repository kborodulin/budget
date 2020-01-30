package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.domain.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}