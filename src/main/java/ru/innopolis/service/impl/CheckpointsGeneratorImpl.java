package ru.innopolis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.domain.Operation;
import ru.innopolis.service.CheckpointsGenerator;
import ru.innopolis.service.DateAnalizer;
import ru.innopolis.service.dto.Point;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
public class CheckpointsGeneratorImpl implements CheckpointsGenerator {
    public static final int CHECKPOINT_COUNT = 10;
    public static final int FIVE_MONTHS = LocalDate.now().lengthOfYear() / 2 - 30;
    private long deltaDays;

    DateAnalizer dateAnalizer;

    @Autowired
    public void setDateAnalizer(DateAnalizer dateAnalizer) {
        this.dateAnalizer = dateAnalizer;
    }

    @Override
    public List<Point> getPointDate(LocalDate startDate, LocalDate endDate) {
        List<Point> pointList = new ArrayList<>();
        deltaDays = ChronoUnit.DAYS.between(startDate, endDate);
        if (deltaDays > FIVE_MONTHS) {
            return pointsByEndOfMonth(pointList, startDate, endDate);
        }
        else {
            long step = Math.round(1.0*deltaDays/ CHECKPOINT_COUNT);
            return pointsByStep(pointList, startDate, endDate, step>0?step:1);
        }
    }

    @Override
    public List<Point> getPointAmount(List<Point> points, List<Operation> operations) {
        int i = 0;
        for (Point point : points) {
            BigDecimal summaryAmount = BigDecimal.ZERO;
            List<Operation> operationByPoint = new ArrayList<>();
            while ((i < operations.size()) &&
                    (point.getDate().isAfter(operations.get(i).getDateoper()) ||
                     point.getDate().isEqual(operations.get(i).getDateoper()))){
                summaryAmount = summaryAmount.add(operations.get(i).getAmount());
                operationByPoint.add(operations.get(i));
                i++;
            }
            point.setAmount(summaryAmount);
            point.setOperations(operationByPoint);
        }
        return points;
    }

    private List<Point> pointsByStep(List<Point> pointList, LocalDate startDate, LocalDate endDate, long step) {
        LocalDate checkpointDate = startDate;
        for (int i = 0; i < deltaDays/step; i++) {
            pointList.add(new Point(checkpointDate, dateAnalizer.getDateName(checkpointDate)));
            checkpointDate = checkpointDate.plusDays(step);
        }
        if (!checkpointDate.minusDays(step).isEqual(endDate)) {
            pointList.add(new Point(endDate, dateAnalizer.getDateName(endDate)));
        }
        return pointList;
    }

    private List<Point>  pointsByEndOfMonth(List<Point> pointList, LocalDate startDate, LocalDate endDate) {
        LocalDate checkpointDate = startDate.with(lastDayOfMonth());
        do {
            pointList.add(new Point(checkpointDate, dateAnalizer.getMonthName(checkpointDate.getMonthValue() - 1)));
            checkpointDate = checkpointDate.plusMonths(1).with(lastDayOfMonth());
        } while (!checkpointDate.getMonth().equals(endDate.getMonth()));
        pointList.add(new Point(checkpointDate, dateAnalizer.getMonthName(checkpointDate.getMonthValue() - 1)));
        return pointList;
    }
}
