package ru.innopolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.innopolis.domain.Account;
import ru.innopolis.domain.Category;
import ru.innopolis.domain.Operation;
import ru.innopolis.domain.User;
import ru.innopolis.service.AccountService;
import ru.innopolis.service.CategoryService;
import ru.innopolis.service.DateAnalizer;
import ru.innopolis.service.OperationService;
import ru.innopolis.service.dto.CategoryPeriod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Доходы
 */
@Controller
public class IncomeController {
    private OperationService operationService;

    private AccountService accountService;

    private CategoryService categoryService;

    private DateAnalizer dateAnalizer;

    public static final int MAX_COUNT_ELEMENT_PAGE = 10;

    private int dateRange;

    private CategoryPeriod categoryPeriod;

    private int findIncome = 0;

    @Autowired
    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
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
    public void setDateAnalizer(DateAnalizer dateAnalizer) {
        this.dateAnalizer = dateAnalizer;
    }

    /**
     * Список доходов пользователя
     */
    @GetMapping("/income")
    public String getAllIncomeUser(Model model, HttpServletRequest request, @ModelAttribute("period") String period, Integer page) {
        User user = (User) request.getSession().getAttribute("user");
        if (page == null) page = 1;
        List<Object[]> operations = operationService.allIncomeUser(user.getUserid(), LocalDate.now(), LocalDate.now(), 0, page);
        model.addAttribute("countPage", page(user.getUserid(), LocalDate.now(), LocalDate.now(), 0));
        model.addAttribute("allincomeuser", operations);
        HttpSession session = request.getSession(true);
        session.setAttribute("allcategoryperiod", null);
        List<Object[]> operationsAll = operationService.allIncomeUser(user.getUserid(), LocalDate.now(), LocalDate.now(), 0, null);
        BigDecimal sumPeriod = BigDecimal.ZERO;
        for (Object[] obj : operationsAll) {
            sumPeriod = sumPeriod.add(((BigDecimal) obj[1]));
        }
        model.addAttribute("intervalperiod", "СУММА ЗА ДЕНЬ ");
        model.addAttribute("sumperiod", sumPeriod + " руб.");
        if (findIncome == 0) {
            session.setAttribute("findallaccountbyusersortfilter",null);
        }
        if (findIncome == 1) {
            findIncome = 0;
        }
        session.setAttribute("isaccount", 1);
        session.setAttribute("curpage", page);
        return "income";
    }

    /**
     * Список доходов пользователя с разбивкой на страницы
     */
    @GetMapping(value = "/income{page}")
    public String listPartPage(Model model,
                               HttpServletRequest request,
                               @ModelAttribute("period") String period,
                               @PathVariable("page") Integer page) {
        getAllIncomeUser(model, request, period, page);
        return "income";
    }

    /**
     * Добавить
     */
    @PostMapping("/income/add")
    public String saveIncome(HttpServletRequest request,
                             @ModelAttribute("addincome") Operation operation,
                             @ModelAttribute("accountid") Account account,
                             @ModelAttribute("categoryid") Category category) {
        if (operation.getOperationid() != null) {
            Operation operationOld = operationService.findById(operation.getOperationid());
            operationService.clearDelete(operationOld);
        }
        account = accountService.findById(account.getAccountid());
        category = categoryService.findById(category.getCategoryid());
        if (operation.getTypeoperationid().equals(1L)) {
            account.setAmount(account.getAmount().add(operation.getAmount()));
        }
        accountService.save(account);
        operation.setAccount(account);
        operation.setCategory(category);
        operationService.save(operation);
        HttpSession session = request.getSession(true);
        session.setAttribute("allcategoryperiod", null);
        return "redirect:/income";
    }

    /**
     * Найти
     */
    @GetMapping("/income/find/{id}")
    public String findIncome(RedirectAttributes attributes, @PathVariable("id") Long id, HttpServletRequest request) {
        Operation operation = operationService.findById(id);
        attributes.addFlashAttribute("findincome", operation);
        User user = (User) request.getSession().getAttribute("user");
        List<Account> accountsByUser = accountService.findAllByUserSort(user.getUserid(), operation.getAccount().getAccountid());
        List<Category> categories = categoryService.findAllSort(operation.getCategory().getCategoryid());
        attributes.addFlashAttribute("findallaccountbyusersort", accountsByUser);
        attributes.addFlashAttribute("findallcategoriessort", categories);
        attributes.addFlashAttribute("findoperationid", operation.getOperationid());
        HttpSession session = request.getSession(true);
        session.setAttribute("findallaccountbyusersortfilter", accountsByUser.get(0).getName() + ": " + accountsByUser.get(0).getAmount() + " руб.");
        findIncome = 1;
        return "redirect:/income";
    }

    /**
     * Редактировать
     */
    @PostMapping("/income/update/{id}")
    public String updateIncome(@ModelAttribute("updincome") Operation operation) {
        operationService.save(operation);
        return "redirect:/income";
    }

    /**
     * Удалить
     */
    @GetMapping("/income/delete/{id}")
    public String deleteIncome(@PathVariable("id") Long id) {
        Operation operation = operationService.findById(id);
        if (operation != null) {
            operationService.clearDelete(operation);
        }
        return "redirect:/income";
    }

    /**
     * Фильтр
     */
    @PostMapping("/income/filter")
    public String filterIncome(Model model,
                               HttpServletRequest request,
                               @ModelAttribute("dateRange") int period,
                               @ModelAttribute("categoryperiod") CategoryPeriod category,
                               Integer page) {
        List<LocalDate> dates = dateAnalizer.parsePeriod(period);
        User user = (User) request.getSession().getAttribute("user");
        if (page == null) page = 1;
        List<Object[]> operations = operationService.allIncomeUser(user.getUserid(), dates.get(0), dates.get(1), category.getCategoryperiod(), page);
        model.addAttribute("countPage", page(user.getUserid(), dates.get(0), dates.get(1), category.getCategoryperiod()));
        model.addAttribute("periodselected", period);
        model.addAttribute("allincomeuser", operations);
        HttpSession session = request.getSession(true);
        session.setAttribute("allcategoryperiod", category.getCategoryperiod());
        if (period > 0 || category.getCategoryperiod() > 0) {
            model.addAttribute("isfilter", 1);
            dateRange = period;
            categoryPeriod = category;
        }
        List<Object[]> operationsAll = operationService.allIncomeUser(user.getUserid(), dates.get(0), dates.get(1), category.getCategoryperiod(), null);
        BigDecimal sumPeriod = BigDecimal.ZERO;
        for (Object[] obj : operationsAll) {
            sumPeriod = sumPeriod.add(((BigDecimal) obj[1]));
        }
        switch (period) {
            case 1: {
                model.addAttribute("intervalperiod", "СУММА ЗА ДЕНЬ ");
                model.addAttribute("sumperiod", sumPeriod + " руб.");
                break;
            }
            case 2: {
                model.addAttribute("intervalperiod", "СУММА ЗА НЕДЕЛЮ ");
                model.addAttribute("sumperiod", sumPeriod + " руб.");
                break;
            }
            case 3: {
                model.addAttribute("intervalperiod", "СУММА ЗА МЕСЯЦ ");
                model.addAttribute("sumperiod", sumPeriod + " руб.");
                break;
            }
            case 4: {
                model.addAttribute("intervalperiod", "СУММА ЗА ГОД ");
                model.addAttribute("sumperiod", sumPeriod + " руб.");
                break;
            }
            case 5: {
                model.addAttribute("intervalperiod", "СУММА ЗА ВЕСЬ ПЕРИОД ");
                model.addAttribute("sumperiod", sumPeriod + " руб.");
                break;
            }
        }
        session.setAttribute("curpage", page);
        return "income";
    }

    /**
     * Фильтр с учетом разбивки на страницы
     */
    @GetMapping(value = "/income/filter{page}")
    public String filterIncomePage(Model model,
                                   HttpServletRequest request,
                                   @PathVariable("page") Integer page) {
        filterIncome(model, request, dateRange, categoryPeriod, page);
        return "income";
    }

    private int page(Long userId, LocalDate dateStart, LocalDate end, int categoryid) {
        int allRecord = operationService.allIncomeUser(userId, dateStart, end, categoryid, null).size();
        return (allRecord % MAX_COUNT_ELEMENT_PAGE == 0) ? allRecord / MAX_COUNT_ELEMENT_PAGE : allRecord / MAX_COUNT_ELEMENT_PAGE + 1;
    }
}