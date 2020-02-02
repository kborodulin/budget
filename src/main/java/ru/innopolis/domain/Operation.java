package ru.innopolis.domain;



import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "operation")
@Data
@DynamicInsert
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationid;

    private Long typeoperationid;

    private Long categoryid;

    private Long accountid;

    private BigDecimal amount;

    private LocalDateTime dateoper;

    private LocalDateTime datewritedb;

    private String comment;
}