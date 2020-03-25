package ru.innopolis.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class Transaction {
    private Long inAccountId;
    private Long outAccountId;
    private BigDecimal outSum;
    private String comment;
    private String outName;
    private String inName;
    private String outOwner;
    private String inOwner;
    private Long outOperationId;
    private Long inOperationId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOper;

    public Transaction() {
    }

    public Transaction(Object[] out, Object[] in){
        outName = (String) out[0];
        inName = (String) in[0];
        outSum = (BigDecimal) out[1];
        dateOper = LocalDate.parse(out[2].toString());
        comment = (String) out[3];
        outOwner = (String) out[6];
        inOwner = (String) in[6];
        outOperationId = ((BigInteger) out[4]).longValue();
        inOperationId = ((BigInteger) in[4]).longValue();
    }

}
