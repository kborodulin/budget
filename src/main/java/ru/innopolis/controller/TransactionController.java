package ru.innopolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.domain.*;
import ru.innopolis.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ru.innopolis.controller.IncomeController.MAX_COUNT_ELEMENT_PAGE;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private AccountService accountService;
    private FamemService famemService;
    private AccountTypeService accountTypeService;
    private UserService userService;
    private CategoryService categoryService;
    private OperationService operationService;
    private DateAnalizer dateAnalizer;
    private int dateRange;

    @GetMapping
    public ModelAndView openTransactions(ModelAndView modelAndView, HttpServletRequest request, Integer page) {
        if (page == null) page = 1;
        Long familyId = getMyFamem(request).getFamily().getFamilyid();
        modelAndView.addObject("countPage", page(familyId, LocalDate.now(), LocalDate.now()));
        HttpSession session = request.getSession(true);
        Object tranpersel = session.getAttribute("tranpersel");
        Integer periodSelected = tranpersel != null ? (Integer) tranpersel : 1;
        modelAndView.addObject("periodselected", periodSelected);
        List<LocalDate> dates = dateAnalizer.parsePeriod(periodSelected);
        List<Transaction> transactions = groupTransactions(operationService.findAllTransactionsByPeriod(familyId, dates.get(0), dates.get(1), page));
        modelAndView.addObject("transactions", transactions);
        modelAndView.setViewName("transactions");
        session.setAttribute("isaccount", 1);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView walletsWithFilter(ModelAndView modelAndView,
                                          HttpServletRequest request,
                                          @ModelAttribute("dateRange") int period) {
        modelAndView.addObject("periodselected", period);
        List<LocalDate> dates = dateAnalizer.parsePeriod(period);
        List<Transaction> transactions = groupTransactions(operationService.findAllTransactionsByPeriod(getMyFamem(request).getFamily().getFamilyid(), dates.get(0), dates.get(1), 1));
        modelAndView.addObject("transactions", transactions);
        modelAndView.setViewName("transactions");
        HttpSession session = request.getSession(true);
        session.setAttribute("tranpersel", period);
        return modelAndView;
    }

    private List<Transaction> groupTransactions(List<Object[]> transactions) {
        List<Transaction> gTransactions = new ArrayList<>();
        transactions.forEach(trans -> {
            if (trans[5].equals(BigInteger.valueOf(3))) {
                transactions.forEach(t -> {
                    if (t[5].equals(BigInteger.valueOf(4)) && t[3].equals(trans[3]) && t[2].equals(trans[2]) && t[1].equals(trans[1])) {
                        gTransactions.add(new Transaction(trans, t));
                    }
                });
            }
        });
        return gTransactions;
    }


    @PostMapping(path = "/getallwallets")
    public ModelAndView getUserWallets(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
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
        modelAndView.setViewName("ref/allmywallets");
        return modelAndView;
    }

    @PostMapping(path = "/savetrans")
    private ModelAndView saveTransaction(@ModelAttribute("transaction") Transaction transaction, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Category category = categoryService.findById(15L);
        LocalDate dateOper = transaction.getDateOper() != null ? transaction.getDateOper() : LocalDate.now();
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

        modelAndView.setViewName("redirect:/transactions");
        return modelAndView;
    }


    private User getMe(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

    private Famem getMyFamem(HttpServletRequest request) {
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

    @Autowired
    public void setDateAnalizer(DateAnalizer dateAnalizer) {
        this.dateAnalizer = dateAnalizer;
    }


    @PostMapping("/filter")
    public String filterTransactions(Model model,
                               HttpServletRequest request,
                               @ModelAttribute("dateRange") int period,
                               Integer page) {
        List<LocalDate> dates = dateAnalizer.parsePeriod(period);
        Long familyId = getMyFamem(request).getFamily().getFamilyid();
        User user = (User) request.getSession().getAttribute("user");
        if (page == null) page = 1;
        List<Object[]> operations = operationService.findAllTransactionsByPeriod(familyId, dates.get(0), dates.get(1), page);
        model.addAttribute("countPage", page(familyId, dates.get(0), dates.get(1)));
        model.addAttribute("periodselected", period);
        model.addAttribute("allincomeuser", operations);
        if (period > 0) {
            model.addAttribute("isfilter", 1);
            dateRange = period;
        }
        List<Object[]> operationsAll = operationService.findAllTransactionsByPeriod(familyId, dates.get(0), dates.get(1), null);
        return "transactions";
    }

    @GetMapping(value = "/filter/{page}")
    public String filterIncomePage(Model model, HttpServletRequest request, @PathVariable("page") Integer page) {
        filterTransactions(model, request, dateRange, page);
        return "transactions";
    }

    private int page(Long familyId, LocalDate dateStart, LocalDate end) {
        int allRecord = operationService.findAllTransactionsByPeriod(familyId, dateStart, end, null).size();
        return (allRecord % MAX_COUNT_ELEMENT_PAGE == 0) ? allRecord / MAX_COUNT_ELEMENT_PAGE : allRecord / MAX_COUNT_ELEMENT_PAGE + 1;
    }
}
