package ru.innopolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.domain.User;
import ru.innopolis.service.UserService;

import java.util.stream.Collectors;

/**
 * RegController
 *
 * @author Ekaterina Belolipetskaya
 */
@Controller
@RequestMapping("/registration")
public class RegController {

    UserService userService;

    Validator regValidator;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public ModelAndView renderRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView userRegistration(@ModelAttribute(name = "userRegistrationForm") @Validated User user,
                                         BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("registration");
            modelAndView.addObject("result", result);
            return modelAndView;
        }
        user.setRoleid(1L);
        user.setIsblock(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRegValidator(Validator regValidator) {
        this.regValidator = regValidator;
    }
}
