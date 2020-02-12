package ru.innopolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innopolis.domain.Account;
import ru.innopolis.domain.Category;
import ru.innopolis.domain.Operation;
import ru.innopolis.domain.User;
import ru.innopolis.service.AccountService;
import ru.innopolis.service.CategoryService;
import ru.innopolis.service.OperationService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

/**
 * Расходы
 */
@Controller
public class ExpensesController {
    private OperationService operationService;

    private AccountService accountService;

    private CategoryService categoryService;

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

    /**
     * Список расходов пользователя
     */
    @GetMapping("/expenses")
    public String getAllExpensesUser(Model model, HttpServletRequest request, @ModelAttribute("period") String period) {
        User user = (User) request.getSession().getAttribute("user");
        List<Operation> operations = operationService.allExpensesUser(user.getFamem().getFamemid(), LocalDate.now(), LocalDate.now());
        model.addAttribute("allExpensesUser", operations);
        return "expenses";
    }

    /**
     * Добавить
     */
    @PostMapping("/expenses/add")
    public String saveExpenses(@ModelAttribute("expensesForm") Operation operation, @ModelAttribute("accountid") Account account,
                               @ModelAttribute("categoryid") Category category) {
        account = accountService.findById(account.getAccountid());
        category = categoryService.findById(category.getCategoryid());
        if (operation.getTypeoperationid().equals(2L)) {
            account.setAmount(account.getAmount().subtract(operation.getAmount()));
        }
        accountService.save(account);
        operation.setAccount(account);
        operation.setCategory(category);
        operationService.save(operation);
        return "redirect:/expenses";
    }

    /**
     * Найти
     */
    @PostMapping("/expenses/find/{id}")
    public String findExpenses(Model model, @PathVariable("id") Long id) {
        Operation operation = operationService.findById(id);
        model.addAttribute("findexpenses", operation);
        return "expenses";
    }

    /**
     * Редактировать
     */
    @PostMapping("/expenses/update/{id}")
    public String updateExpenses(@ModelAttribute("updateexpenses") Operation operation, @PathVariable("id") Long id) {
        operationService.save(operation);
        return "expenses";
    }

    /**
     * Удалить
     */
    @GetMapping("/expenses/delete/{id}")
    public String deleteExpenses(@PathVariable("id") Long id) {
        Operation operation = operationService.findById(id);
        operationService.clearDelete(operation);
        return "redirect:/expenses";
    }
}