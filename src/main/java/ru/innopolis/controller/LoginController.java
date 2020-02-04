package ru.innopolis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ModelAndView renderLogin(@RequestParam(defaultValue = "false") String error) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", error);
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
