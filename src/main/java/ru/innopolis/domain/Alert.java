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

    @ManyToOne
    @JoinColumn(name = "alertinitid")
    private Famem initiator;

    @ManyToOne
    @JoinColumn(name = "famemid")
    private Famem receiver;

    @ManyToOne
    @JoinColumn(name = "familyid")
    private Family family;

    private BigDecimal status;

    private BigDecimal isalertsignproc;

    public boolean isAlertSignProc(){
        return BigDecimal.ONE.compareTo(isalertsignproc)==0;
    }
}