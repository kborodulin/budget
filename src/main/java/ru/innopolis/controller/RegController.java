package ru.innopolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.controller.db.model.User;
import ru.innopolis.service.RegService;
import ru.innopolis.service.dto.UserDto;

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

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView renderRegistrationForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView userRegistration(@ModelAttribute(name = "userForm") UserDto userDto){
        ModelAndView modelAndView = new ModelAndView();
        regService.registerUser(userDto);
        return modelAndView;
    }

    @ModelAttribute
    public User persistUser() {
        return new User();
    }
}
