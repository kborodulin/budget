package ru.innopolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.innopolis.domain.Currency;
import ru.innopolis.service.AccountService;
import ru.innopolis.service.CurrencyService;
import ru.innopolis.service.KinshipService;
import ru.innopolis.service.RoleService;

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
    private AccountService accountService;

    @Autowired
    private CurrencyService currencyService;

    /**
     * Справочник ролей
     */
    @GetMapping("/ref/allrole")
    public String getAllRole(Model model, String page) {
        model.addAttribute("refallrole", roleService.findAll());
        return page;
    }

    /**
     * Справочник родства
     */
    @GetMapping("/ref/allkinship")
    public String getAllKinship(Model model, String page) {
        model.addAttribute("refallkinship", kinshipService.findAll());
        return page;
    }

    /**
     * Справочник типов счетов
     */
    @GetMapping("/ref/allaccounttype")
    public String getAllAccountType(Model model, String page) {
        model.addAttribute("refallaccounttype", accountService.findAll());
        return page;
    }

    /**
     * Справочник валют
     */
    @GetMapping("/ref/allcurrency")
    public String getAllCurrency(Model model, String page) {
        List<Currency> currencies = currencyService.findAll().stream()
                .filter(x -> x.getBrief().equals("rub"))
                .collect(Collectors.toList());
        model.addAttribute("refallcurrency", currencies);
        return page;
    }
}