package ru.innopolis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.domain.User;
import ru.innopolis.repository.UserRepository;
import ru.innopolis.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        LOGGER.info("saved user to DB {}", user);
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        LOGGER.info("find user by ID {}", id);
        return userRepository.findById(id).get();
    }

    @Override
    public void delete(User user) {
        LOGGER.info("delete user from DB {}", user);
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("find all users");
        return userRepository.findAll();
    }

    @Override
    public User findFirstByEmail(String email) {
        LOGGER.info("find user by email {}", email);
                return userRepository.findFirstByEmail(email);
    }

    @Override
    public User findFirstByLogin(String login) {
        LOGGER.info("find user by login {}", login);
                return userRepository.findFirstByLogin(login);
    }
}