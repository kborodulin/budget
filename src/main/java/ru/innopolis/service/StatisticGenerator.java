package ru.innopolis.service;

import ru.innopolis.service.dto.Point;
import ru.innopolis.service.dto.FilterStatistic;

import java.util.List;

public interface StatisticGenerator {
    List<Point> getData(FilterStatistic filterStatistic);
}
