package ru.innopolis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        List<Point> points = statisticGenerator.getData(filterStatistic);
        model.addAttribute("points", points);
        return "/statistic";
    }

    @ModelAttribute
    public void persistModel(Model model, HttpServletRequest request, @ModelAttribute("filterStatistic") FilterStatistic filterStatistic) {
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
        model.addAttribute("operationList", operationList);

        LocalDate startDate = dateAnalizer.parsePeriod(Periods.MONTH).get(0);
        model.addAttribute("startDate", startDate);

        LocalDate endDate = LocalDate.now();
        model.addAttribute("endDate", endDate);

        if (filterStatistic.getFamilyMembers() == null || filterStatistic.getCategoryList() == null) {
            filterStatistic.setFamilyMembers(familyMembers.stream().map(Famem::getFamemid).collect(Collectors.toList()));
            filterStatistic.setCategoryList(categoryList.stream().map(Category::getCategoryid).collect(Collectors.toList()));
            filterStatistic.setStartDate(startDate);
            filterStatistic.setEndDate(endDate);
            filterStatistic.setFlagAllFamilyMembers(ALL_FLAG);
            filterStatistic.setFlagAllCategories(ALL_FLAG);
            model.addAttribute("filterStatistic", filterStatistic);
        }
    }

    @PostMapping("/statistic")
    public ModelAndView filterStatistic(@ModelAttribute("filterStatistic") @Validated FilterStatistic filterStatistic, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("statistic");
            modelAndView.addObject("filterStatistic", filterStatistic);
            modelAndView.addObject("result", result);
            return modelAndView;
        }
        List<Point> points = statisticGenerator.getData(filterStatistic);
        modelAndView.addObject("filterStatistic", filterStatistic);
        modelAndView.addObject("points", points);
        modelAndView.setViewName("statistic");
        return modelAndView;
    }

    @InitBinder(value = "filterStatistic")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(filterStatisticValidator);
    }

}
