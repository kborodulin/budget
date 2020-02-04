package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.domain.TypeOperation;

@Repository
public interface TypeOperationRepository extends JpaRepository<TypeOperation, Long> {
}