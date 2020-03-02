package ru.innopolis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.domain.*;
import ru.innopolis.enums.Periods;
import ru.innopolis.service.*;
import ru.innopolis.service.dto.FilterStatistic;
import ru.innopolis.service.dto.Point;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static ru.innopolis.controller.IncomeController.MAX_COUNT_ELEMENT_PAGE;

@Controller
@Slf4j
public class StatisticController {

    public static final String ALL_FLAG = "all";
    public static final int FIRST_PAGE = 1;
    FamemService famemService;
    OperationService operationService;
    AccountService accountService;
    CategoryService categoryService;
    DateAnalizer dateAnalizer;
    StatisticGenerator statisticGenerator;
    Validator filterStatisticValidator;

    @Autowired
    public StatisticController(FamemService famemService, OperationService operationService, AccountService accountService,
                               CategoryService categoryService, DateAnalizer dateAnalizer, StatisticGenerator statisticGenerator,
                               @Qualifier("filterStatisticValidator") Validator filterStatisticValidator) {
        this.famemService = famemService;
        this.operationService = operationService;
        this.accountService = accountService;
        this.categoryService = categoryService;
        this.dateAnalizer = dateAnalizer;
        this.statisticGenerator = statisticGenerator;
        this.filterStatisticValidator = filterStatisticValidator;
    }

    @GetMapping("/statistic")
    public String renderMain(@ModelAttribute("filterStatistic") FilterStatistic filterStatistic, Model model) {
        if (filterStatistic.getFamilyMembers() != null) {
            List<Point> points = statisticGenerator.getData(filterStatistic);
            model.addAttribute("points", points);
            List<Operation> operations = operationService.getOperationsByFamemsAndCategories(filterStatistic.getFamilyMembers(), filterStatistic.getCategoryList(),
                    filterStatistic.getStartDate(), filterStatistic.getEndDate(), filterStatistic.getOperationType(), 0);
            model.addAttribute("operations", operations);
            model.addAttribute("pages", countPages(filterStatistic));
            model.addAttribute("currentPage", FIRST_PAGE);
        }
        return "/statistic";
    }

    @GetMapping("/statistic/{page}")
    public String renderMain(@ModelAttribute("filterStatistic") FilterStatistic filterStatistic, Model model, @PathVariable("page") Integer page) {
        if (filterStatistic.getFamilyMembers() != null) {
            List<Point> points = statisticGenerator.getData(filterStatistic);
            model.addAttribute("points", points);
            List<Operation> operations = operationService.getOperationsByFamemsAndCategories(filterStatistic.getFamilyMembers(), filterStatistic.getCategoryList(),
                    filterStatistic.getStartDate(), filterStatistic.getEndDate(), filterStatistic.getOperationType(), (page-1));
            model.addAttribute("operations", operations);
            model.addAttribute("pages", countPages(filterStatistic));
            model.addAttribute("currentPage", page);
        }
        return "/statistic";
    }

    @ModelAttribute
    public void persistModel(Model model, HttpServletRequest request, @ModelAttribute("filterStatistic") FilterStatistic filterStatistic) {
        User user = (User) request.getSession().getAttribute("user");
        FilterStatistic sessionFilterStatistic = (FilterStatistic) request.getSession().getAttribute("filterStatistic");
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

        if (filterStatistic.getFamilyMembers() == null || filterStatistic.getCategoryList() == null) {
            if (sessionFilterStatistic != null){
                filterStatistic = sessionFilterStatistic;
            } else {
                LocalDate startDate = dateAnalizer.parsePeriod(Periods.MONTH).get(0);
                LocalDate endDate = dateAnalizer.parsePeriod(Periods.MONTH).get(1);

                filterStatistic.setFamilyMembers(familyMembers.stream().map(Famem::getFamemid).collect(Collectors.toList()));
                filterStatistic.setCategoryList(categoryList.stream().map(Category::getCategoryid).collect(Collectors.toList()));
                filterStatistic.setStartDate(startDate);
                filterStatistic.setEndDate(endDate);
                filterStatistic.setFlagAllFamilyMembers(ALL_FLAG);
                filterStatistic.setFlagAllCategories(ALL_FLAG);
                if (filterStatistic.getOperationType() == 0) {
                    filterStatistic.setOperationType(2);
                }
            }
        }
        request.getSession().setAttribute("filterStatistic", filterStatistic);
        model.addAttribute("filterStatistic", filterStatistic);
    }

    @PostMapping("/statistic")
    public ModelAndView filterStatistic(@ModelAttribute("filterStatistic") @Validated FilterStatistic filterStatistic, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("statistic");
            modelAndView.addObject("result", result);
            return modelAndView;
        }
        List<Point> points = statisticGenerator.getData(filterStatistic);
        modelAndView.addObject("points", points);
        List<Operation> operations = operationService.getOperationsByFamemsAndCategories(filterStatistic.getFamilyMembers(), filterStatistic.getCategoryList(),
                filterStatistic.getStartDate(), filterStatistic.getEndDate(), filterStatistic.getOperationType(), 0);
        modelAndView.addObject("operations", operations);
        modelAndView.addObject("pages", countPages(filterStatistic));
        modelAndView.addObject("currentPage", FIRST_PAGE);
        modelAndView.setViewName("statistic");
        return modelAndView;
    }

    @InitBinder(value = "filterStatistic")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(filterStatisticValidator);
    }

    private int countPages(FilterStatistic filterStatistic){
        int operationsCount = operationService.getOperationsByFamemsAndCategories(filterStatistic.getFamilyMembers(), filterStatistic.getCategoryList(),
                filterStatistic.getStartDate(), filterStatistic.getEndDate(), filterStatistic.getOperationType(), null).size();
        return (operationsCount % MAX_COUNT_ELEMENT_PAGE == 0) ? operationsCount / MAX_COUNT_ELEMENT_PAGE : operationsCount / MAX_COUNT_ELEMENT_PAGE + 1;
    }
}
