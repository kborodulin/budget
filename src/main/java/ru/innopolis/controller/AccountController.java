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
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.Family;
import ru.innopolis.domain.User;
import ru.innopolis.service.FamemService;
import ru.innopolis.service.FamilyService;
import ru.innopolis.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/account")
public class AccountController {

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
    public ModelAndView saveNewPersonalInfo(@ModelAttribute("personalInfoForm") Famem famem, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        log.info("saved changes in famem {}", famem);
        famem.setUserid(user.getUserid());
        famemService.update(famem);
        modelAndView.setViewName("redirect:/account");
        return modelAndView;
    }

    @PostMapping(value = "/savefamily")
    public ModelAndView saveNewFamily(@ModelAttribute("familyInfo") @Validated Family family, BindingResult result,
                                      HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.addObject("result", result);
            modelAndView.setViewName("redirect:/account");
            return modelAndView;
        }
        Famem famem = famemService.getByUserid(user.getUserid());
        log.info("save new family {}", family);
        familyService.create(family, user.getLogin());
        modelAndView.setViewName("redirect:/account");
        return modelAndView;
    }

    @ModelAttribute
    public void persistUser(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        log.debug("get user from session {}", user);
        Famem famem = famemService.getByUserid(user.getUserid());
        model.addAttribute("famem", famem);
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
