package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.Family;
import ru.innopolis.domain.User;

import java.util.List;

@Repository
public interface FamemRepository extends JpaRepository<Famem, Long> {
    Famem findFirstByUser(User user);
    List<Famem> findAllByFamily(Family family);
}