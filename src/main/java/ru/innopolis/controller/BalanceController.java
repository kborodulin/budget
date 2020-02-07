package ru.innopolis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.innopolis.domain.User;
import ru.innopolis.service.AccountService;

import javax.servlet.http.HttpServletRequest;

/**
 * Баланс по счету
 */
@Controller
public class BalanceController {
    @Autowired
    AccountService accountService;

    @GetMapping("/balance")
    public void getAllIncomeUser(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("balance", accountService.balance(user.getUserid()));
    }
}
