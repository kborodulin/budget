package ru.innopolis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/panel")
public class PanelController {

    @GetMapping
    public ModelAndView renderMainPanel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("personalAccount");
        return modelAndView;
    }
}
