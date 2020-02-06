package ru.innopolis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.domain.Account;
import ru.innopolis.service.AccountService;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/wallet")
public class WalletController {

    AccountService accountService;

    @GetMapping
    public ModelAndView openWallet(ModelAndView modelAndView) {
        modelAndView.setViewName("wallet");
        return modelAndView;
    }

    @PostMapping(path = "/create")
    public ModelAndView createNewWallet(@ModelAttribute("createNewAccountForm") @Validated Account account, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        accountService.save(account);
        modelAndView.setViewName("redirect:/wallet");
        return modelAndView;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
