package ru.innopolis.service;

import ru.innopolis.enums.Periods;

import java.time.LocalDate;
import java.util.List;

public interface DateAnalizer {
    List<LocalDate> parsePeriod(String period);

    List<LocalDate> parsePeriod(Periods period);
}
