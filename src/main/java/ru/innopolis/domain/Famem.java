package ru.innopolis.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "famem")
@Data
public class Famem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long famemid;

    private String surname;

    private String name;

    private String patronymic;

    private LocalDateTime datebirth;

    private String shortname;

    private Long familyid;

    private Long userid;

    private Long kinshipid;
}
