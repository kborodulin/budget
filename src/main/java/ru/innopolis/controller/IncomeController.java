package ru.innopolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innopolis.domain.Operation;
import ru.innopolis.domain.User;
import ru.innopolis.service.OperationService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

/**
 * Доходы
 */
@Controller
public class IncomeController {
    @Autowired
    private OperationService operationService;

    /**
     * Список доходов пользователя
     */
    @GetMapping("/income")
    public String getAllIncomeUser(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Object[]> operations = operationService.allIncomeUser(user.getUserid(), LocalDate.now(), LocalDate.now());
        model.addAttribute("allincomeuser", operations);
        return "income";
    }

    /**
     * Добавить
     */
    @PostMapping("/income/add")
    public String saveIncome(@ModelAttribute("addincome") Operation operation) {
        operation.setTypeoperationid(1L);
        operationService.save(operation);
        return "redirect:/income";
    }

    /**
     * Найти
     */
    @PostMapping("/income/find/{id}")
    public String findIncome(Model model, @PathVariable("id") Long id) {
        Operation operation = operationService.findById(id);
        model.addAttribute("findincome", operation);
        return "income";
    }

    /**
     * Редактировать
     */
    @PostMapping("/income/update/{id}")
    public String updateIncome(@ModelAttribute("updateincome") Operation operation) {
        operationService.save(operation);
        return "income";
    }

    /**
     * Удалить
     */
    @GetMapping("/income/delete/{id}")
    public String deleteIncome(@PathVariable("id") Long id) {
        Operation operation = operationService.findById(id);
        operationService.delete(operation);
        return "income";
    }
}