package ru.innopolis.domain;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "alert")
@Data
@DynamicInsert
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertid;

    private LocalDateTime datealert;

    private Long alertinitid;

    private Long familyid;

    private Long famemid;

    private BigDecimal status;

    private BigDecimal isalertsignproc;

    private Long initiator;

    private Long receiver;

    public boolean isAlertSignProc(){
        return BigDecimal.ONE.compareTo(isalertsignproc)==0;
    }
}