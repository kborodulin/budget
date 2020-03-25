package ru.innopolis.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * о компании
 */
@Controller
public class Company {

    @GetMapping("/company")
    public String company(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("isaccount", null);
        return "/company";
    }
}
