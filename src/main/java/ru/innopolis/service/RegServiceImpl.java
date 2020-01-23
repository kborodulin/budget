package ru.innopolis.service;

import org.springframework.stereotype.Service;
import ru.innopolis.db.dao.UserDao;
import ru.innopolis.db.model.User;
import ru.innopolis.service.dto.UserDto;

import java.util.UUID;

/**
 * RegServiceImpl
 *
 * @author Ekaterina Belolipetskaya
 */
@Service
public class RegServiceImpl implements RegService {
    private UserDao userDao;

    public RegServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDto registerUser(UserDto userDto) {
        User user = new User(UUID.randomUUID(), userDto.getUsername(), userDto.getPassword(), userDto.getEmail());
        userDao.addUser(user);
        userDto.setId(user.getId());
        return userDto;
    }
}
