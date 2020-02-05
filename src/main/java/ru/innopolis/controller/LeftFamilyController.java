package ru.innopolis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.domain.User;
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

    @Autowired
    public LeftFamilyController(FamemService famemService, FamilyService familyService) {
        this.famemService = famemService;
        this.familyService = familyService;
    }

    @GetMapping()
    public ModelAndView leftFamily(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        famemService.removeFamilyRef(user.getUserid());
        modelAndView.setViewName("redirect:/account");
        return modelAndView;
    }
}
