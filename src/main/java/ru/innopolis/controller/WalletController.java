package ru.innopolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.domain.*;
import ru.innopolis.service.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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
    CategoryService categoryService;
    OperationService operationService;

    @GetMapping
    public ModelAndView openWallet(ModelAndView modelAndView) {
        modelAndView.setViewName("wallet");
        return modelAndView;
    }

    @PostMapping ModelAndView walletsWithFilter(ModelAndView modelAndView){

        modelAndView.setViewName("wallet");
        return modelAndView;
    }

    @PostMapping(path = "/remove")
    public ModelAndView deleteWallet(@ModelAttribute("deletewallet") Account account){
        ModelAndView modelAndView = new ModelAndView();
        account = accountService.findById(account.getAccountid());
        account.setIsclosesign(new BigDecimal(1));
        accountService.save(account);
        modelAndView.setViewName("redirect:/wallet");
        return modelAndView;
    }

    @PostMapping(path = "/recover")
    public ModelAndView recoverWallet(@ModelAttribute("recoverwallet") Account account){
        ModelAndView modelAndView = new ModelAndView();
        account = accountService.findById(account.getAccountid());
        account.setIsclosesign(new BigDecimal(0));
        accountService.save(account);
        modelAndView.setViewName("redirect:/wallet");
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

    @PostMapping(path="/savetrans")
    private ModelAndView saveTransaction(@ModelAttribute("transaction") Transaction transaction, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Category category = categoryService.findById(15L);
        LocalDate dateOper = LocalDate.now();
        Account accountOut = accountService.findById(transaction.getOutAccountId());
        Account accountIn = accountService.findById(transaction.getInAccountId());

        Operation operationOut = new Operation();
        operationOut.setTypeoperationid(3L);
        operationOut.setCategory(category);
        operationOut.setAmount(transaction.getOutSum());
        operationOut.setDateoper(dateOper);
        operationOut.setComment(transaction.getComment());
        operationOut.setAccount(accountOut);

        Operation operationIn = new Operation();
        operationIn.setTypeoperationid(4L);
        operationIn.setCategory(category);
        operationIn.setAmount(transaction.getOutSum());
        operationIn.setDateoper(dateOper);
        operationIn.setComment(transaction.getComment());
        operationIn.setAccount(accountIn);

        operationService.save(operationOut);
        operationService.save(operationIn);

        accountOut.setAmount(accountOut.getAmount().subtract(transaction.getOutSum()));
        accountIn.setAmount(accountIn.getAmount().add(transaction.getOutSum()));

        accountService.save(accountIn);
        accountService.save(accountOut);

        modelAndView.setViewName("redirect:/wallet");
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

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
    }
}
