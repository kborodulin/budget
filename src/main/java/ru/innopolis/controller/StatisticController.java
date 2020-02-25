package ru.innopolis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.domain.*;
import ru.innopolis.service.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.innopolis.controller.IncomeController.MAX_COUNT_ELEMENT_PAGE;

@Controller
@Slf4j
public class StatisticController {

    FamemService famemService;
    OperationService operationService;
    AccountService accountService;
    CategoryService categoryService;
    DateAnalizer dateAnalizer;

    @Autowired
    public StatisticController(FamemService famemService, OperationService operationService, AccountService accountService, CategoryService categoryService, DateAnalizer dateAnalizer) {
        this.famemService = famemService;
        this.operationService = operationService;
        this.accountService = accountService;
        this.categoryService = categoryService;
        this.dateAnalizer = dateAnalizer;
    }

    @GetMapping("/statistic")
    public String renderMain() {
        return "/statistic";
    }

    @ModelAttribute
    public void persistModel(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Famem famem = famemService.findByUser(user);
        Family family = famem.getFamily();
        model.addAttribute("family", family);

        if (family == null) {
            return;
        }

        List<Famem> familyMembers = famemService.findAllByFamily(family);
        model.addAttribute("familyMembers", familyMembers);

        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);

        Pageable pageable = PageRequest.of(0, MAX_COUNT_ELEMENT_PAGE);
        List<Operation> operationList = operationService.getTopFamilyOperations(family, pageable);
        model.addAttribute("operationList",operationList);

    }
}
