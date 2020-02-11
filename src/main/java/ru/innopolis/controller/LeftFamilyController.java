package ru.innopolis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.Family;
import ru.innopolis.domain.User;
import ru.innopolis.service.AlertService;
import ru.innopolis.service.FamemService;
import ru.innopolis.service.FamilyService;

import javax.servlet.http.HttpServletRequest;


/**
 * LeftFamilyController
 *
 * @author Ekaterina Belolipetskaya
 */
@Controller
@Slf4j
@RequestMapping("/leftfamily")
public class LeftFamilyController {
    FamemService famemService;
    FamilyService familyService;
    AlertService alertService;

    @Autowired
    public LeftFamilyController(FamemService famemService, FamilyService familyService, AlertService alertService) {
        this.famemService = famemService;
        this.familyService = familyService;
        this.alertService = alertService;
    }

    @GetMapping()
    public ModelAndView leftFamily(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        Famem famem = famemService.findById(user.getFamem().getFamemid());
        Family family = famem.getFamily();
        famem.setFamily(null);
        famemService.save(famem);
        if (family.getFamemList().size() == 1) {
            alertService.deleteByFamily(family);
            familyService.delete(family);
        }
        modelAndView.setViewName("redirect:/account");
        return modelAndView;
    }
}
