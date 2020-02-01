package ru.innopolis.service;

import ru.innopolis.domain.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findById(Long id);

    void delete(User user);

    List<User> findAll();

    User findFirstByEmail(String email);

    User findFirstByLogin(String login);

    Long getUseridByLogin(String username);
}
