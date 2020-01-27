package ru.innopolis.domain;

import javax.persistence.*;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long id;

    // userid
    //          datecreate
    //  login
    //          password
    //  email
    //          isblock
    //   roleid


}
