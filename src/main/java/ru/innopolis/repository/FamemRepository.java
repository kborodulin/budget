package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.domain.Famem;

@Repository
public interface FamemRepository extends JpaRepository<Famem, Long> {
}