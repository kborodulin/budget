package ru.innopolis.db.model;

import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class Family {
    private Long id;
    private String name;

    public Family() {
    }

    public Family(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Family)) return false;
        Family family = (Family) o;
        return Objects.equals(id, family.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Family{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
