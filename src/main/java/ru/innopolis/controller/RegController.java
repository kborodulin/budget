package ru.innopolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.User;
import ru.innopolis.service.UserService;

/**
 * RegController
 *
 * @author Ekaterina Belolipetskaya
 */
@Controller
@RequestMapping("/registration")
public class RegController {
    @Autowired
    UserService userService;
    @Autowired
    @Qualifier("registrationValidator")
    Validator regValidator;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView renderRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView userRegistration(@ModelAttribute(name = "userRegistrationForm") @Validated User user,
                                         BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("registration");
            modelAndView.addObject("result", result);
            modelAndView.addObject("regUser", user);
            return modelAndView;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Famem famem = new Famem();
        famem.setUser(user);
        user.setFamem(famem);
        userService.save(user);
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    @ModelAttribute
    public User persistUser() {
        return new User();
    }

    @InitBinder(value = "userRegistrationForm")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(regValidator);
    }
}
