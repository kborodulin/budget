package ru.innopolis.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.innopolis.domain.Category;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.Family;
import ru.innopolis.domain.User;
import ru.innopolis.enums.Periods;
import ru.innopolis.service.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * MainController
 *
 * @author Ekaterina Belolipetskaya
 */
@Controller
@Slf4j
public class MainController {

    FamemService famemService;
    OperationService operationService;
    AccountService accountService;
    CategoryService categoryService;
    DateAnalizer dateAnalizer;

    @Autowired
    public MainController(FamemService famemService, OperationService operationService, AccountService accountService, CategoryService categoryService, DateAnalizer dateAnalizer) {
        this.famemService = famemService;
        this.operationService = operationService;
        this.accountService = accountService;
        this.categoryService = categoryService;
        this.dateAnalizer = dateAnalizer;
    }

    @GetMapping("/main")
    public String renderMain() {
        return "/main";
    }

    @ModelAttribute
    public void persistModel(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Famem famem = famemService.findByUser(user);
        Family family = famem.getFamily();
        model.addAttribute("family", family);

        List<Famem> familyMembers = famemService.findAllByFamily(family);
        model.addAttribute("familyMembers", familyMembers);

        List<LocalDate> period = dateAnalizer.parsePeriod(Periods.MONTH);

        model.addAttribute("month", dateAnalizer.getMonthName(LocalDate.now().getMonthValue()-1));

        List<BigDecimal> familyMembersExpenses = operationService.findMembersExpenses(familyMembers, period);
        model.addAttribute("familyMembersExpenses", familyMembersExpenses);

        List<Category> categoryList = categoryService.findAll();
        List<BigDecimal> familyCategoryExpenses = operationService.findAllCategoryExpenses(family, categoryList, period);
        Map<String, String> familyCategoryExpensesMap = new HashMap<>();
        for (int i = 0; i < categoryList.size(); i++) {
            if (familyCategoryExpenses.get(i).compareTo(BigDecimal.ZERO) != 0) {
                familyCategoryExpensesMap.put(categoryList.get(i).getName(), familyCategoryExpenses.get(i).toString());
            }
        }
        model.addAttribute("familyCategoryExpensesMap", familyCategoryExpensesMap);

        BigDecimal summaryAllFamilyExpenses = operationService.getSummaryExpenses(family, period);
        model.addAttribute("summaryAllFamilyExpenses", summaryAllFamilyExpenses);

        BigDecimal summaryAllFamilyIncome = operationService.getSummaryIncome(family, period);
        model.addAttribute("summaryAllFamilyIncome", summaryAllFamilyIncome);

        List<BigDecimal> familyMembersBalance = familyMembers
                .stream().map(a -> accountService.famemBalance(a)).collect(Collectors.toList());
        model.addAttribute("familyMembersBalance", familyMembersBalance);

        BigDecimal familyBalance = familyMembersBalance.stream().filter(Objects::nonNull).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        model.addAttribute("familyBalance", familyBalance);

    }
}
