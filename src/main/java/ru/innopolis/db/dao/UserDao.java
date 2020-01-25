package ru.innopolis.db.dao;

import ru.innopolis.db.model.User;

public interface UserDao {
    void addUser(User user);

    User getUserByEmail(String email);

    User getUserByUsername(String username);
}
