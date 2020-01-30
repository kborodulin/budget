package ru.innopolis.service;

import ru.innopolis.domain.Role;

import java.util.List;

public interface RoleService {
    void save(Role role);

    Role findById(Long id);

    void delete(Role role);

    List<Role> findAll();
}