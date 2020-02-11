package ru.innopolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.innopolis.domain.*;
import ru.innopolis.service.*;

import javax.servlet.http.HttpServletRequest;
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
    public List<Role> getAllRole(Model model) {
        return roleService.findAll();
    }

    /**
     * Справочник родства
     */
    @GetMapping("/ref/allkinship")
    public List<Kinship> getAllKinship(Model model) {
        return kinshipService.findAll();
    }

    /**
     * Справочник типов счетов
     */
    @GetMapping("/ref/allaccounttype")
    public void getAllAccountType(Model model) {
        model.addAttribute("refallaccounttype",  accountTypeService.findAll());
    }

    /**
     * Справочник валют
     */
    @GetMapping("/ref/allcurrency")
    public List<Currency> getAllCurrency(Model model) {
        List<Currency> currencies = currencyService.findAll().stream()
                .filter(x -> x.getBrief().equals("rub"))
                .collect(Collectors.toList());
        return currencies;
    }

    /**
     * Справочник категорий
     */
    @GetMapping("/ref/allcategory")
    public void getAllCategory(Model model) {
        model.addAttribute("refallcategory", categoryService.findAll());
    }

    /**
     * Справочник типов операций
     */
    @GetMapping("/ref/alltypeoperation")
    public List<TypeOperation> getAllTypeOperation(Model model) {
        return typeOperationService.findAll();
    }

    /**
     * Список счетов пользователя
     */
    @GetMapping("/ref/accountbyuser")
    public void getAllOperationByUser(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Account> accountsByUser = accountService.findAllByUser(user.getUserid());
        model.addAttribute("findallaccountbyuser", accountsByUser);
    }
}