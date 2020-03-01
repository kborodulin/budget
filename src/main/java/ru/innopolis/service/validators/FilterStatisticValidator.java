package ru.innopolis.service.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.innopolis.service.dto.FilterStatistic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service("filterStatisticValidator")
@Slf4j
public class FilterStatisticValidator implements Validator {

    public static final int WEEK = 6;

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterStatistic.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FilterStatistic filterStatistic = (FilterStatistic) o;
        log.debug("validation for FilterStatistic data started {}", filterStatistic);

        LocalDate startDate = filterStatistic.getStartDate();
        LocalDate endDate = filterStatistic.getEndDate();

        if (startDate == null) {
            log.error("start date is null");
            errors.rejectValue("startDate", "filterStatistic.startNull", "Заполните дату начала корректым значением");
            return;
        }
        if (endDate == null) {
            log.error("end date is null");
            errors.rejectValue("endDate", "filterStatistic.endNull", "Заполните дату окончания корректым значением");
            return;
        }

        long deltaDays = ChronoUnit.DAYS.between(startDate, endDate);

        if (deltaDays < 0) {
            log.error("period in filer for statistic is incorrect, days {}", deltaDays);
            errors.rejectValue("startDate", "filterStatistic.incorrectPeriod", "Дата начала должна быть раньше даты окончания");
            return;
        }

        if (deltaDays < WEEK) {
            log.error("period in filer for statistic is too short, days {}", deltaDays);
            errors.rejectValue("startDate", "filterStatistic.shortPeriod", "Указанный период слишком короткий");
            return;
        }

        if (deltaDays > startDate.lengthOfYear()) {
            log.error("period in filer for statistic is too long, days {}", deltaDays);
            errors.rejectValue("endDate", "filterStatistic.longPeriod", "Указанный период слишком длинный");
            return;
        }

        log.info("validation for family data finished successfully");
    }
}
