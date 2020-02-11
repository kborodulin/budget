package ru.innopolis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.domain.Account;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.User;
import ru.innopolis.service.AccountService;
import ru.innopolis.service.FamemService;
import ru.innopolis.service.UserService;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/wallet")
public class WalletController {

    AccountService accountService;
    FamemService famemService;

    @GetMapping
    public ModelAndView openWallet(ModelAndView modelAndView) {
        modelAndView.setViewName("wallet");
        return modelAndView;
    }

    @PostMapping(path = "/create")
    public ModelAndView createNewWallet(@ModelAttribute("createNewAccountForm") @Validated Account account,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        account.setFamemid(getMyFamemId(request));
        accountService.save(account);
        modelAndView.setViewName("redirect:/wallet");
        return modelAndView;
    }

    @PostMapping(path = "/getallwallets")
    public ModelAndView getUserWallets(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        List<Account> accounts = accountService.findByFamemid(getMyFamemId(request));
        modelAndView.addObject("accounts", accounts);
        modelAndView.setViewName("ref/allmywallets");
        return modelAndView;
    }

    @Autowired
    public void setFamemService(FamemService famemService) {
        this.famemService = famemService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private Long getMyId(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        return user.getUserid();
    }

    private Long getMyFamemId(HttpServletRequest request){
        return famemService.getByUserid(getMyId(request)).getFamemid();
    }
}
