package ru.innopolis.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    private String login;

    private String password;

    private String email;

    private int isblock;

    private Long roleid;
}