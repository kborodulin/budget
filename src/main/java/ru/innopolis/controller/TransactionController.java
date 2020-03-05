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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.innopolis.controller.IncomeController.MAX_COUNT_ELEMENT_PAGE;

@Controller
@RequestMapping(value = {"/transactions", "/transactions/{optionalPage}"})
public class TransactionController {

    private AccountService accountService;
    private FamemService famemService;
    private AccountTypeService accountTypeService;
    private UserService userService;
    private CategoryService categoryService;
    private OperationService operationService;
    private DateAnalizer dateAnalizer;
    private int dateRange;

    @GetMapping()
    public ModelAndView openTransactions(ModelAndView modelAndView, HttpServletRequest request, @PathVariable Optional<Integer> optionalPage, @RequestParam(required = false) String range) {
        Integer page = 1;
        HttpSession session = request.getSession(true);
        if (range == null) {
            range = (String) session.getAttribute("range");
            if (range == null) range = "1";
        } else session.setAttribute("range", range);
        if (optionalPage.isPresent()) {
            page = optionalPage.get();
            session.setAttribute("tranpage", page);
        } else if (session.getAttribute("tranpage") != null) {
            page = (Integer) session.getAttribute("tranpage");
        }
        Family myFamily = getMyFamem(request).getFamily();
        Long familyId = myFamily != null ? myFamily.getFamilyid() : null;
        Integer periodSelected = Integer.valueOf(range);
        modelAndView.addObject("periodselected", periodSelected);
        List<LocalDate> dates = dateAnalizer.parsePeriod(periodSelected);
        Long userid = getMe(request).getUserid();
        List<Transaction> transactions = groupTransactions(operationService.findAllTransactionsByPeriod(familyId, dates.get(0), dates.get(1), page, userid));
        modelAndView.addObject("countPage", page(familyId, dates.get(0), dates.get(1), userid));
        modelAndView.addObject("curpage", page);
        modelAndView.addObject("transactions", transactions);
        modelAndView.setViewName("transactions");
        session.setAttribute("isaccount", 1);
        return modelAndView;
    }


    private List<Transaction> groupTransactions(List<Object[]> transactions) {
        List<Transaction> gTransactions = new ArrayList<>();
        List<Object[]> used = new ArrayList<>();
        transactions.forEach(trans -> {
            if (!used.contains(trans) && trans[5].equals(BigInteger.valueOf(3))) {
                transactions.forEach(t -> {
                    if (!used.contains(t) && !used.contains(trans) && t[5].equals(BigInteger.valueOf(4)) && t[3].equals(trans[3]) && t[2].equals(trans[2]) && t[1].equals(trans[1])) {
                        used.add(t);
                        used.add(trans);
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
        BigDecimal transactionSum = transaction.getOutSum();

        Operation operationOut;
        Operation operationIn;

        Long outOpId = transaction.getOutOperationId();
        Long inOpid = transaction.getInOperationId();

        Operation oldOperationOut = outOpId != null ? operationService.findById(transaction.getOutOperationId()) : null;
        Operation oldOperationIn = inOpid != null ? operationService.findById(transaction.getInOperationId()) : null;

        BigDecimal deltaSum;

        if (oldOperationOut != null && oldOperationIn != null) {
            operationIn = oldOperationIn;
            operationOut = oldOperationOut;
            deltaSum = transactionSum.subtract(operationIn.getAmount());

            Account oldAccountIn = operationIn.getAccount();
            Account oldAccountOut = operationOut.getAccount();

            if (!oldAccountIn.getAccountid().equals(accountIn.getAccountid())) {
                oldAccountIn.setAmount(oldAccountIn.getAmount().subtract(operationIn.getAmount()));
                accountIn.setAmount(accountIn.getAmount().add(operationIn.getAmount()));
                accountService.save(oldAccountIn);
            }

            if (!oldAccountOut.getAccountid().equals(accountOut.getAccountid())) {
                oldAccountOut.setAmount(oldAccountOut.getAmount().add(operationOut.getAmount()));
                accountOut.setAmount(accountOut.getAmount().subtract(operationOut.getAmount()));
                accountService.save(oldAccountOut);
            }
        } else {
            operationOut = new Operation();
            operationIn = new Operation();
            deltaSum = transactionSum;
        }

        accountOut.setAmount(accountOut.getAmount().subtract(deltaSum));
        accountIn.setAmount(accountIn.getAmount().add(deltaSum));

        operationOut.setTypeoperationid(3L);
        operationOut.setCategory(category);
        operationOut.setAmount(transaction.getOutSum());
        operationOut.setDateoper(dateOper);
        operationOut.setComment(transaction.getComment());
        operationOut.setAccount(accountOut);

        operationIn.setTypeoperationid(4L);
        operationIn.setCategory(category);
        operationIn.setAmount(transaction.getOutSum());
        operationIn.setDateoper(dateOper);
        operationIn.setComment(transaction.getComment());
        operationIn.setAccount(accountIn);

        operationService.save(operationOut);
        operationService.save(operationIn);
        accountService.save(accountIn);
        accountService.save(accountOut);

        modelAndView.setViewName("redirect:/transactions");
        return modelAndView;
    }

    @PostMapping(path = "/remove")
    private ModelAndView removeTransaction(@ModelAttribute("deltransa") Transaction transaction) {
        ModelAndView modelAndView = new ModelAndView();
        if (transaction.getOutOperationId() != null) {
            Operation outOperation = operationService.findById(transaction.getOutOperationId());
            if (outOperation != null) {
                operationService.delete(outOperation);
                Account outAccount = outOperation.getAccount();
                outAccount.setAmount(outAccount.getAmount().add(outOperation.getAmount()));
                accountService.save(outAccount);
            }
        }
        if (transaction.getInOperationId() != null) {
            Operation inOperation = operationService.findById(transaction.getInOperationId());
            if (inOperation != null) {
                operationService.delete(inOperation);
                Account inAccount = inOperation.getAccount();
                inAccount.setAmount(inAccount.getAmount().subtract(inOperation.getAmount()));
                accountService.save(inAccount);
            }
        }
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
        Famem myFamem = getMyFamem(request);
        Long familyId = myFamem.getFamily().getFamilyid();
        User user = (User) request.getSession().getAttribute("user");
        if (page == null) page = 1;
        List<Object[]> operations = operationService.findAllTransactionsByPeriod(familyId, dates.get(0), dates.get(1), page, user.getUserid());
        model.addAttribute("countPage", page(familyId, dates.get(0), dates.get(1), user.getUserid()));
        model.addAttribute("periodselected", period);
        model.addAttribute("allincomeuser", operations);
        if (period > 0) {
            model.addAttribute("isfilter", 1);
            dateRange = period;
        }
        List<Object[]> operationsAll = operationService.findAllTransactionsByPeriod(familyId, dates.get(0), dates.get(1), null, user.getUserid());
        return "transactions";
    }

    @GetMapping(value = "/filter/{page}")
    public String filterIncomePage(Model model, HttpServletRequest request, @PathVariable("page") Integer page) {
        filterTransactions(model, request, dateRange, page);
        return "transactions";
    }

    private int page(Long familyId, LocalDate dateStart, LocalDate end, Long userid) {
        int allRecord = operationService.findAllTransactionsByPeriod(familyId, dateStart, end, null, userid).size() / 2;
        return (allRecord % MAX_COUNT_ELEMENT_PAGE == 0) ? allRecord / MAX_COUNT_ELEMENT_PAGE : allRecord / MAX_COUNT_ELEMENT_PAGE + 1;
    }
}
