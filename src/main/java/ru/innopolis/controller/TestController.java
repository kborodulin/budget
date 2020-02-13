package ru.innopolis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @GetMapping(value = "/main")
    public ModelAndView openMain(ModelAndView modelAndView) {
        modelAndView.setViewName("/main");
        return modelAndView;
    }

    @GetMapping(value = "/statistic")
    public ModelAndView openStatistic(ModelAndView modelAndView) {
        modelAndView.setViewName("statistic");
        return modelAndView;
    }
}