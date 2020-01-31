package ru.innopolis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.domain.Famem;
import ru.innopolis.service.FamemService;
import ru.innopolis.service.UserService;

@Controller
@Slf4j
@RequestMapping("/account")
public class PersonalInfoController {

    FamemService famemService;
    UserService userService;

    @GetMapping
    public ModelAndView renderPersonalAccount() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account");
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

    @ModelAttribute
    public void persistUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("famem", famemService.getByLogin(user.getUsername()));
        model.addAttribute("user", userService.findFirstByLogin(user.getUsername()));
    }

    @Autowired
    public void setFamemService(FamemService famemService) {
        this.famemService = famemService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
