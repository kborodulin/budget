package ru.innopolis.service.impl;

import ru.innopolis.enums.Periods;
import ru.innopolis.service.DateAnalizer;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.innopolis.enums.Periods.*;

/**
 * DateAnalizerImpl
 *
 * @author Ekaterina Belolipetskaya
 */
public class DateAnalizerImpl implements DateAnalizer {

    public static final int DAYS_OF_WEEK = 6;
    public static final String FIRST_DAY_OF_LIFE = "1900-01-01";

    /**
     * Parse defined period to star and end dates.
     *
     * @param period - today, week, month ets. values as string.
     * @return list that contains star and end value for defined period.
     */
    @Override
    public List<LocalDate> parsePeriod(String period) {
        LocalDate today = LocalDate.now();
        if (Objects.equals(TODAY.name().toLowerCase(), period)) {
            return parseTodayPeriod(today);
        }
        if (Objects.equals(WEEK.name().toLowerCase(), period)) {
            return parseWeekPeriod(today);
        }
        if (Objects.equals(MONTH.name().toLowerCase(), period)) {
            return parseMonthPeriod(today);
        }
        if (Objects.equals(YEAR.name().toLowerCase(), period)) {
            return parseYearPeriod(today);
        }
        if (Objects.equals(LIFE.name().toLowerCase(), period)) {
            return parseLifePeriod(today);
        }
        return parseTodayPeriod(today);
    }

    /**
     * Parse defined period to star and end dates.
     *
     * @param period - today, week, month ets. values as enum value of Period.
     * @return list that contains star and end value for defined period.
     */
    @Override
    public List<LocalDate> parsePeriod(Periods period) {
        LocalDate today = LocalDate.now();
        if (Objects.equals(TODAY, period)) {
            return parseTodayPeriod(today);
        }
        if (Objects.equals(WEEK, period)) {
            return parseWeekPeriod(today);
        }
        if (Objects.equals(MONTH, period)) {
            return parseMonthPeriod(today);
        }
        if (Objects.equals(YEAR, period)) {
            return parseYearPeriod(today);
        }
        if (Objects.equals(LIFE, period)) {
            return parseLifePeriod(today);
        }
        return parseTodayPeriod(today);
    }

    private List<LocalDate> parseLifePeriod(LocalDate today) {
        LocalDate start = LocalDate.parse(FIRST_DAY_OF_LIFE);
        LocalDate end = today.withDayOfYear(today.lengthOfYear());
        return Arrays.asList(start, end);
    }

    private List<LocalDate> parseYearPeriod(LocalDate today) {
        LocalDate start = today.withDayOfYear(1);
        LocalDate end = today.withDayOfYear(today.lengthOfYear());
        return Arrays.asList(start, end);
    }

    private List<LocalDate> parseMonthPeriod(LocalDate today) {
        LocalDate start = today.withDayOfMonth(1);
        LocalDate end = today.withDayOfMonth(today.lengthOfMonth());
        return Arrays.asList(start, end);
    }

    private List<LocalDate> parseWeekPeriod(LocalDate today) {
        LocalDate start = today.with(ChronoField.DAY_OF_WEEK, 1);
        LocalDate end = start.plusDays(DAYS_OF_WEEK);
        return Arrays.asList(start, end);
    }

    private List<LocalDate> parseTodayPeriod(LocalDate today) {
        return Arrays.asList(today, today);
    }
}
