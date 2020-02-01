package ru.innopolis.service.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.innopolis.domain.Family;
import ru.innopolis.service.FamilyService;

@Service("familyValidator")
@Slf4j
public class FamilyValidator implements Validator {
    FamilyService familyService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Family.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Family family = (Family) o;
        log.debug("validation for family data started {}", family);

        if (familyService.findByName(family.getName()) != null) {
            log.error("family with the same name already exists {}", family);
            errors.rejectValue("name", "familyName.exists", "Семья с таким именем уже зарегистрирована");
            return;
        }

        log.info("validation for family data finished successfully");
    }

    @Autowired
    public void setFamemService(FamilyService familyService) {
        this.familyService = familyService;
    }
}
