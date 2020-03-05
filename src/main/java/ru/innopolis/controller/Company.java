package ru.innopolis.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * о компании
 */
@Controller
public class Company {

    @GetMapping("/company")
    public String company() {
        return "/company";
    }
}
