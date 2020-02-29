package ru.innopolis.service.dto;

import lombok.Data;
import ru.innopolis.domain.Operation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class Point {
    LocalDate date;
    String dateBrief;
    BigDecimal amount;
    List<Operation> operations;

    public Point(LocalDate date, String dateBrief) {
        this.dateBrief = dateBrief;
        this.date = date;
    }
}
