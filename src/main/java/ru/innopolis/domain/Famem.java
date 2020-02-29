package ru.innopolis.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "famem")
@Data
@DynamicInsert
public class Famem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long famemid;

    private String surname;

    private String name;

    private String patronymic;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datebirth;

    private String shortname;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "familyid")
    private Family family;

    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User user;

    private Long kinshipid;

    @OneToMany(mappedBy = "famem", fetch = FetchType.LAZY)
    private List<Account> accountList;

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<Alert> alertListForReceiver;

    @Override
    public String toString() {
        return "Famem{" +
                "famemid=" + famemid +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", datebirth=" + datebirth +
                ", shortname='" + shortname + '\'' +
                ", kinshipid=" + kinshipid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Famem)) return false;
        Famem famem = (Famem) o;
        return Objects.equals(famemid, famem.famemid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(famemid);
    }
}
