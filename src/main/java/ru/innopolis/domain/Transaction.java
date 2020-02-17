package ru.innopolis.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {

    private Long inAccountId;
    private Long outAccountId;
    private BigDecimal outSum;
    private String comment;

}
