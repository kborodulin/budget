package ru.innopolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.innopolis.domain.*;
import ru.innopolis.service.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Справочники из бд
 */
@Controller
public class ReferenceController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private KinshipService kinshipService;

    @Autowired
    private AccountTypeService accountTypeService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TypeOperationService typeOperationService;

    /**
     * Справочник ролей
     */
    @GetMapping("/ref/allrole")
    public List<Role> getAllRole(Model model, String page) {
        return roleService.findAll();
    }

    /**
     * Справочник родства
     */
    @GetMapping("/ref/allkinship")
    public List<Kinship> getAllKinship(Model model, String page) {
        return kinshipService.findAll();
    }

    /**
     * Справочник типов счетов
     */
    @GetMapping("/ref/allaccounttype")
    public List<AccountType> getAllAccountType(Model model, String page) {
        return accountTypeService.findAll();
    }

    /**
     * Справочник валют
     */
    @GetMapping("/ref/allcurrency")
    public List<Currency> getAllCurrency(Model model, String page) {
        List<Currency> currencies = currencyService.findAll().stream()
                .filter(x -> x.getBrief().equals("rub"))
                .collect(Collectors.toList());
        return currencies;
    }

    /**
     * Справочник категорий
     */
    @GetMapping("/ref/allcategory")
    public List<Category> getAllCategory(Model model) {
        model.addAttribute("refallcategory", categoryService.findAll());
        return categoryService.findAll();
    }

    /**
     * Справочник типов операций
     */
    @GetMapping("/ref/alltypeoperation")
    public List<TypeOperation> getAllTypeOperation(Model model, String page) {
        return typeOperationService.findAll();
    }
}