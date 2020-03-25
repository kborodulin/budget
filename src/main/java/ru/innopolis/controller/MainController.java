package ru.innopolis.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.innopolis.domain.*;
import ru.innopolis.enums.Periods;
import ru.innopolis.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.innopolis.controller.IncomeController.MAX_COUNT_ELEMENT_PAGE;

/**
 * MainController
 *
 * @author Ekaterina Belolipetskaya
 */
@Controller
@Slf4j
public class MainController {

    public static final int LIMIT_MAIN_OPERATIONS = 10;
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
    public String renderMain(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("isaccount", null);
        return "/main";
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

        List<LocalDate> period = dateAnalizer.parsePeriod(Periods.MONTH);

        model.addAttribute("month", dateAnalizer.getMonthName(LocalDate.now().getMonthValue() - 1));

        List<BigDecimal> familyMembersExpenses = operationService.findMembersExpenses(familyMembers, period);
        if (familyMembersExpenses.size() == 0) familyMembersExpenses = null;
        model.addAttribute("familyMembersExpenses", familyMembersExpenses);

        List<Category> categoryList = categoryService.findAll();
        List<BigDecimal> familyCategoryExpenses = operationService.findAllCategoryExpenses(family, categoryList, period);
        Map<String, String> familyCategoryExpensesMap = new HashMap<>();
        for (int i = 0; i < categoryList.size(); i++) {
            if (familyCategoryExpenses.get(i).compareTo(BigDecimal.ZERO) != 0) {
                familyCategoryExpensesMap.put(categoryList.get(i).getName(), familyCategoryExpenses.get(i).toString());
            }
        }
        if (familyCategoryExpensesMap.size() == 0) familyCategoryExpensesMap = null;
        model.addAttribute("familyCategoryExpensesMap", familyCategoryExpensesMap);

        BigDecimal summaryAllFamilyExpenses = operationService.getSummaryExpenses(family, period);
        model.addAttribute("summaryAllFamilyExpenses", summaryAllFamilyExpenses);

        BigDecimal summaryAllFamilyIncome = operationService.getSummaryIncome(family, period);
        model.addAttribute("summaryAllFamilyIncome", summaryAllFamilyIncome);

        BigDecimal percentOfExpenses = BigDecimal.ZERO;
        if (summaryAllFamilyIncome.compareTo(BigDecimal.ZERO) != 0) {
            percentOfExpenses = summaryAllFamilyExpenses.multiply(BigDecimal.valueOf(100)).divide(summaryAllFamilyIncome, 2);
        }
        model.addAttribute("percentOfExpenses", percentOfExpenses);

        List<BigDecimal> familyMembersBalance = familyMembers
                .stream().map(a -> accountService.famemBalance(a)).collect(Collectors.toList());

        Map<String, BigDecimal> familyMembersBalanceMap = new HashMap<>();
        for (int i = 0; i < familyMembers.size(); i++) {
            familyMembersBalanceMap.put(familyMembers.get(i).getUser().getLogin(),familyMembersBalance.get(i));
        }
        model.addAttribute("familyMembersBalanceMap", familyMembersBalanceMap);

        BigDecimal familyBalance = familyMembersBalance.stream().filter(Objects::nonNull).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        model.addAttribute("familyBalance", familyBalance);

        Pageable pageable = PageRequest.of(0, MAX_COUNT_ELEMENT_PAGE);
        List<Operation> operationList = operationService.getTopFamilyOperations(family, pageable);
        if ((operationList== null) || (operationList.size() == 0)) operationList = null;
        model.addAttribute("operationList", operationList);
    }
}
