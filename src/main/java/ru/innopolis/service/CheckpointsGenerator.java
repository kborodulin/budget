package ru.innopolis.service;

import ru.innopolis.domain.Operation;
import ru.innopolis.service.dto.Point;

import java.time.LocalDate;
import java.util.List;

public interface CheckpointsGenerator {
    List<Point> getPointDate(LocalDate startDate, LocalDate endDate);

    List<Point> getPointAmount(List<Point> dates, List<Operation> operations);
}