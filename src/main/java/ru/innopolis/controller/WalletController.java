package ru.innopolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.domain.Account;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.Family;
import ru.innopolis.domain.User;
import ru.innopolis.service.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/wallet")
public class WalletController {

    private AccountService accountService;
    private FamemService famemService;
    private AccountTypeService accountTypeService;
    private UserService userService;
    private CategoryService categoryService;
    private OperationService operationService;
    private DateAnalizer dateAnalizer;

    @GetMapping
    public ModelAndView openWallet(ModelAndView modelAndView, HttpServletRequest request, @RequestParam(required = false) String err ) {
        Famem myFamem = getMyFamem(request);
        List<Account> accounts = accountService.findAllByFamem(myFamem);
        Family myFamily = myFamem.getFamily();
        List<Famem> allFamems = new ArrayList<>();
        allFamems.add(myFamem);
        if (myFamily != null) {
            List<Famem> famems = famemService.findAllByFamily(myFamily);
            allFamems.addAll(famems);
            famems.stream().filter(f -> !f.getUser().getUserid().equals(myFamem.getUser().getUserid())).forEach(f -> {
                accounts.addAll(accountService.findAllByFamem(f));
            });
        }
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("famems", allFamems);
        modelAndView.addObject("myfamem", myFamem);
        modelAndView.addObject("accounts", accounts);
        modelAndView.addObject("types", accountTypeService.findAll());
        modelAndView.addObject("err", err);
        modelAndView.setViewName("wallet");
        return modelAndView;
    }


    @PostMapping(path = "/remove")
    public ModelAndView deleteWallet(@ModelAttribute("deletewallet") Account account) {
        ModelAndView modelAndView = new ModelAndView();
        account = accountService.findById(account.getAccountid());
        account.setIsclosesign(new BigDecimal(1));
        accountService.save(account);
        modelAndView.setViewName("redirect:/wallet");
        return modelAndView;
    }

    @PostMapping(path = "/recover")
    public ModelAndView recoverWallet(@ModelAttribute("recoverwallet") Account account) {
        ModelAndView modelAndView = new ModelAndView();
        account = accountService.findById(account.getAccountid());
        account.setIsclosesign(new BigDecimal(0));
        accountService.save(account);
        modelAndView.setViewName("redirect:/wallet");
        return modelAndView;
    }

    @PostMapping(path = "/create")
    public ModelAndView createNewWallet(@ModelAttribute("createNewAccountForm") Account account, HttpServletRequest request, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        List<String> errors = new ArrayList<>();
        if (accountService.findAccountByName(account.getName()).size() == 0) {
            account.setFamem(getMyFamem(request));
            account.setDateopen(LocalDate.now());
            account.setCurrencyid(1L);
            account.setAccounttype(accountTypeService.findById(account.getAcctypeid()));
            accountService.save(account);
        } else {
            errors.add("Ошибка создания счета. Счет с таким именем уже существует");
            modelAndView.addObject("err", errors);
        }
        modelAndView.setViewName("redirect:/wallet");
        return modelAndView;
    }


    private Famem getMyFamem(HttpServletRequest request) {
        return famemService.findByUser(getMe(request));
    }

    private User getMe(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
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

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
    }

    @Autowired
    public void setDateAnalizer(DateAnalizer dateAnalizer) {
        this.dateAnalizer = dateAnalizer;
    }

}
