package ru.innopolis.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table(name = "kinship")
@Data
@DynamicInsert
public class Kinship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kinshipid;

    private String name;

    private String brief;
}
