package ru.innopolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.db.model.User;
import ru.innopolis.service.RegService;
import ru.innopolis.service.RegValidationService;

/**
 * RegController
 *
 * @author Ekaterina Belolipetskaya
 */
@Controller
@RequestMapping("/registration")
public class RegController {
    @Autowired
    RegService regService;
    @Autowired
    RegValidationService regValidator;

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
        regService.registerUser(user);
        if (result.hasErrors()) {
            modelAndView.setViewName("registration");
            modelAndView.addObject("result", result);
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/personalAccount");
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
