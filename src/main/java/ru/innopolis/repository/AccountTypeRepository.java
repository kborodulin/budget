package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.domain.AccountType;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
}