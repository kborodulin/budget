package ru.innopolis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/personalaccount")
public class PersonalInfoController {

    @GetMapping
    public ModelAndView renderPersonalAccount() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("personalAccount");
        return modelAndView;
    }
}
