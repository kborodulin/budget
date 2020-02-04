package ru.innopolis.domain;



import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table(name = "typeoperation")
@Data
@DynamicInsert
public class TypeOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeoperationid;

    private String name;

    private String brief;
}