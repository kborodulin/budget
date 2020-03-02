package ru.innopolis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.domain.Operation;
import ru.innopolis.service.CheckpointsGenerator;
import ru.innopolis.service.OperationService;
import ru.innopolis.service.StatisticGenerator;
import ru.innopolis.service.dto.FilterStatistic;
import ru.innopolis.service.dto.Point;

import java.util.List;

@Service
public class StatisticGeneratorImpl implements StatisticGenerator {
    CheckpointsGenerator checkpointsGenerator;
    OperationService operationService;


    @Autowired
    public StatisticGeneratorImpl(CheckpointsGenerator checkpointsGenerator, OperationService operationService) {
        this.checkpointsGenerator = checkpointsGenerator;
        this.operationService = operationService;
    }

    @Override
    public List<Point> getData(FilterStatistic filterStatistic) {
        List<Point> points = checkpointsGenerator.getPointDate(filterStatistic.getStartDate(), filterStatistic.getEndDate());
        List<Operation> operations = operationService.getOperationsByFamemsAndCategories(filterStatistic.getFamilyMembers(), filterStatistic.getCategoryList(), filterStatistic.getStartDate(), filterStatistic.getEndDate(),
                filterStatistic.getOperationType(), null);
        points = checkpointsGenerator.getPointAmount(points, operations);
        return points;
    }
}