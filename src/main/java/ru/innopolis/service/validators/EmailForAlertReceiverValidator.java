package ru.innopolis.service.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.innopolis.domain.Alert;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.User;
import ru.innopolis.service.AlertService;
import ru.innopolis.service.UserService;

@Service("emailValidator")
@Slf4j
public class EmailForAlertReceiverValidator implements Validator {
    UserService userService;
    AlertService alertService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        log.debug("validation for email started {}", user.getEmail());

        if (userService.findFirstByEmail(user.getEmail()) == null) {
            log.error("user with the such email is absent {}", user.getEmail());
            errors.rejectValue("email", "email.absent", "Пользователь с таким email не зарегистрирован в системе");
            return;
        }

        user = userService.findFirstByEmail(user.getEmail());
        Famem famem = user.getFamem();

        if (famem.getFamily() != null) {
            log.error("user is in the family  {}", user.getEmail());
            errors.rejectValue("email", "family.exist", "Пользователь уже состоит в семье");
            return;
        }

        Alert alert = alertService.checkForAlerts(famem);

        if (alert != null) {
            log.error("user has been already invited to the family  {}", alert.getFamily());
            errors.rejectValue("email", "alert.exist", "Пользователь уже приглашен в семью");
            return;
        }

        log.info("validation for email data finished successfully");
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAlertService(AlertService alertService) {
        this.alertService = alertService;
    }
}
