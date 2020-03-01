package ru.innopolis.service.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class FilterStatistic {
    List<Long> familyMembers;
    List<Long> categoryList;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
    String flagAllFamilyMembers;
    String flagAllCategories;
    int operationType;
}
