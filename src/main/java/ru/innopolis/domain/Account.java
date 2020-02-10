package ru.innopolis.domain;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "account")
@Data
@DynamicInsert
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountid;

    private BigDecimal num;

    private BigDecimal amount;

    private LocalDate dateopen;

    private String accountopenorg;

    private BigDecimal isclosesign;

    private Long famemid;

    private Long accounttypeid;

    private Long currencyid;

    private String name;
}