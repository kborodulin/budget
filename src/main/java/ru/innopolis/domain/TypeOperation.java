package ru.innopolis.domain;



import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "typeoperation")
@Data
public class TypeOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeoperationid;

    private String name;

    private String brief;
}