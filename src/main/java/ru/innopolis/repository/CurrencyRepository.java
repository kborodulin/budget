package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.domain.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}