package ru.innopolis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.innopolis.db.dao.UserDao;
import ru.innopolis.db.model.User;

/**
 * RegServiceImpl
 *
 * @author Ekaterina Belolipetskaya
 */
@Service
public class RegValidationService implements Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegValidationService.class);
    private static final int PASSWORD_LENGTH = 8;
    @Autowired
    private UserDao userDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User userFromUserForm = (User) o;

        if (userDao.getUserByEmail(userFromUserForm.getEmail()) != null) {
            LOGGER.error("user with the same email already exists {}", userFromUserForm);
            errors.rejectValue("email", "email.exists", "Пользователь с таким email уже зарегистрирован");
            return;
        }

        if (userDao.getUserByUsername(userFromUserForm.getUsername()) != null) {
            LOGGER.error("user with the same username already exists {}", userFromUserForm);
            errors.rejectValue("username", "username.exists", "Пользователь с таким логином уже зарегистрирован");
            return;
        }

        if (userFromUserForm.getPassword().length() < PASSWORD_LENGTH) {
            LOGGER.error("password is short {}", userFromUserForm);
            errors.rejectValue("password", "password.short", "Пароль должен быть минимум 8 символов");
        }
    }
}
