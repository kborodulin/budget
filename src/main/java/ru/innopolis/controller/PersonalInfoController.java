package ru.innopolis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.Family;
import ru.innopolis.service.FamemService;
import ru.innopolis.service.FamilyService;
import ru.innopolis.service.UserService;

@Controller
@Slf4j
@RequestMapping("/account")
public class PersonalInfoController {

    FamemService famemService;
    UserService userService;
    FamilyService familyService;
    Validator familyValidator;

    @GetMapping
    public ModelAndView renderPersonalAccount() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/account");
        return modelAndView;
    }

    @PostMapping(value = "/saveinfo")
    public ModelAndView saveNewPersonalInfo(@ModelAttribute("personalInfoForm") Famem famem,
                                            @AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        log.info("saved changes in famem {}", famem);
        famem.setUserid(userService.getUseridByLogin(user.getUsername()));
        famemService.update(famem);
        modelAndView.setViewName("redirect:/account");
        return modelAndView;
    }

    @PostMapping(value = "/saveFamily")
    public ModelAndView saveNewFamily(@ModelAttribute("familyInfo") @Validated Family family, BindingResult result,
                                      @AuthenticationPrincipal User user){
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.addObject("result", result);
            modelAndView.setViewName("redirect:/account");
            return modelAndView;
        }
        Famem famem = famemService.getByLogin(user.getUsername());
        log.info("save new family {}", family);
        familyService.create(family, user.getUsername());
        modelAndView.setViewName("redirect:/account");
        return modelAndView;
    }

    @ModelAttribute
    public void persistUser(@AuthenticationPrincipal User user, Model model) {
        Famem famem = famemService.getByLogin(user.getUsername());
        model.addAttribute("famem", famem);
        model.addAttribute("user", userService.findFirstByLogin(user.getUsername()));
        if (famem.getFamilyid() != null) {
            model.addAttribute("family", familyService.findById(famem.getFamilyid()));
        }
    }

    @InitBinder(value = "familyInfo")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(familyValidator);
    }

    @Autowired
    public void setFamemService(FamemService famemService) {
        this.famemService = famemService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setFamilyService(FamilyService familyService) {
        this.familyService = familyService;
    }

    @Autowired
    @Qualifier("familyValidator")
    public void setFamilyValidator(Validator familyValidator) {
        this.familyValidator = familyValidator;
    }
}
