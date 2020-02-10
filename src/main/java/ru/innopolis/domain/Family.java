package ru.innopolis.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "family")
@Data
@DynamicInsert
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyid;

    private String name;

    @OneToMany(mappedBy = "family", fetch = FetchType.EAGER)
    private List<Famem> famemList;
}
