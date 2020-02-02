package ru.innopolis.domain;



import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    private String login;

    private String password;

    private String email;

    private Integer isblock;

    private Long roleid;
}