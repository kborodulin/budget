package ru.innopolis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.innopolis.domain.User;

/**
 * RegServiceImpl
 *
 * @author Ekaterina Belolipetskaya
 */
@Service
public class RegistrationValidator implements Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationValidator.class);
    private static final int PASSWORD_LENGTH = 8;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User userFromUserForm = (User) o;
        LOGGER.debug("validation for registration data started {}", userFromUserForm);

        if (userService.findFirstByEmail(userFromUserForm.getEmail()) != null) {
            LOGGER.error("user with the same email already exists {}", userFromUserForm);
            errors.rejectValue("email", "email.exists", "Пользователь с таким email уже зарегистрирован");
            return;
        }

        if (userService.findFirstByLogin(userFromUserForm.getLogin()) != null) {
            LOGGER.error("user with the same login already exists {}", userFromUserForm);
            errors.rejectValue("login", "login.exists", "Пользователь с таким логином уже зарегистрирован");
            return;
        }

        if (userFromUserForm.getPassword().length() < PASSWORD_LENGTH) {
            LOGGER.error("password is short {}", userFromUserForm);
            errors.rejectValue("password", "password.short", "Пароль должен быть минимум " + PASSWORD_LENGTH + " символов");
            return;
        }

        LOGGER.debug("validation for registration data finished");

    }
}
