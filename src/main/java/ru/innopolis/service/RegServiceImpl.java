package ru.innopolis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.innopolis.db.dao.UserDao;
import ru.innopolis.db.model.User;

/**
 * RegServiceImpl
 *
 * @author Ekaterina Belolipetskaya
 */
@Service
public class RegServiceImpl implements RegService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegServiceImpl.class);
    private UserDao userDao;

    public RegServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void registerUser(User user) {
        LOGGER.info("add user{}", user);
        userDao.addUser(user);
    }
}
