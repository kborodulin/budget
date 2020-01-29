package ru.innopolis.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "kinship")
@Data
public class Kinship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kinshipid;

    private String name;

    private String brief;
}
