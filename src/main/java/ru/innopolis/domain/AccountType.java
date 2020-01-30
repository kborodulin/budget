package ru.innopolis.domain;



import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "accounttype")
@Data
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accounttypeid;

    private String name;

    private String brief;
}