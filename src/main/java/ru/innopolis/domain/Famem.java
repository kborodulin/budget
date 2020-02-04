package ru.innopolis.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "famem")
@Data
@DynamicInsert
public class Famem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long famemid;

    private String surname;

    private String name;

    private String patronymic;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datebirth;

    private String shortname;

    private Long familyid;

    private Long userid;

    private Long kinshipid;
}
