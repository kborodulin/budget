package ru.innopolis.domain;



import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table(name = "accounttype")
@Data
@DynamicInsert
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accounttypeid;

    private String name;

    private String brief;
}