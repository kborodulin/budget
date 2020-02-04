package ru.innopolis.service;

import ru.innopolis.domain.Famem;

import java.util.List;

public interface FamemService {
    void save(Famem famem);

    Famem findById(Long id);

    void delete(Famem famem);

    List<Famem> findAll();

    Famem findByUserid(Long id);

    Famem getByUserid(Long id);

    void update(Famem famem);

    Famem getByLogin(String username);

    void setFamilyid(String login, Long familyid);
}