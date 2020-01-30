package ru.innopolis.domain;



import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "alert")
@Data
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
}