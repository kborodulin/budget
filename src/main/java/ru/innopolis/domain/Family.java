package ru.innopolis.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "family")
@Data
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyid;

    private String name;
}
