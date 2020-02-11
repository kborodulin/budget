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

    @ManyToOne
    @JoinColumn(name = "famemid", nullable = false)
    private Famem famem;

    @ManyToOne
    @JoinColumn(name = "accounttypeid", nullable = false)
    private AccountType accounttype;

    private Long currencyid;

    private String name;

    @Transient
    private Long acctypeid;
}