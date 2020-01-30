package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.domain.Kinship;

@Repository
public interface KinshipRepository  extends JpaRepository<Kinship, Long> {
}
