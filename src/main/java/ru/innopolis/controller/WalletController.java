package ru.innopolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.domain.Account;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.Family;
import ru.innopolis.domain.User;
import ru.innopolis.service.AccountService;
import ru.innopolis.service.AccountTypeService;
import ru.innopolis.service.FamemService;
import ru.innopolis.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/wallet")
public class WalletController {

    AccountService accountService;
    FamemService famemService;
    AccountTypeService accountTypeService;
    UserService userService;


    @GetMapping
    public ModelAndView openWallet(ModelAndView modelAndView) {
        modelAndView.setViewName("wallet");
        return modelAndView;
    }

    @PostMapping(path = "/create")
    public ModelAndView createNewWallet(@ModelAttribute("createNewAccountForm") Account account, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        account.setFamem(getMyFamem(request));
        account.setDateopen(LocalDate.now());
        account.setCurrencyid(1L);
        account.setAccounttype(accountTypeService.findById(account.getAcctypeid()));
        accountService.save(account);
        modelAndView.setViewName("redirect:/wallet");
        return modelAndView;
    }

    @PostMapping(path = "/getallwallets")
    public ModelAndView getUserWallets(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Famem myFamem = getMyFamem(request);
        List<Account> accounts = accountService.findAllByFamem(myFamem);
        Family myFamily = myFamem.getFamily();
        List<Famem> allFamems = new ArrayList<>();
        allFamems.add(myFamem);
        if (myFamily != null){
            List<Famem> famems = famemService.findAllByFamily(myFamily);
            allFamems.addAll(famems);
            famems.stream().filter(f->!f.getUser().getUserid().equals(myFamem.getUser().getUserid())).forEach(f->{
                accounts.addAll(accountService.findAllByFamem(f));
            });
        }
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("famems", allFamems);
        modelAndView.addObject("myfamem", myFamem);
        modelAndView.addObject("accounts", accounts);
        modelAndView.addObject("types", accountTypeService.findAll());
        modelAndView.setViewName("ref/allmywallets");
        return modelAndView;
    }

    private User getMe(HttpServletRequest request){
        return (User) request.getSession().getAttribute("user");
    }

    private Famem getMyFamem(HttpServletRequest request){
        return famemService.findByUser(getMe(request));
    }


    @Autowired
    public void setFamemService(FamemService famemService) {
        this.famemService = famemService;
    }

    @Autowired
    public void setAccountTypeService(AccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

}
