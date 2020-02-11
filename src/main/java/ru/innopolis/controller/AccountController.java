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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.innopolis.domain.Alert;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.Family;
import ru.innopolis.domain.User;
import ru.innopolis.service.AlertService;
import ru.innopolis.service.FamemService;
import ru.innopolis.service.FamilyService;
import ru.innopolis.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/account")
public class AccountController {

    FamemService famemService;
    UserService userService;
    FamilyService familyService;
    AlertService alertService;
    Validator familyValidator;
    Validator emailValidator;

    @GetMapping
    public ModelAndView renderPersonalAccount() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/account");
        return modelAndView;
    }

    @PostMapping(value = "/saveinfo")
    public ModelAndView saveNewPersonalInfo(@ModelAttribute("personalInfoForm") Famem famemInfo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        log.info("saved changes in famem {}", famemInfo);
        Famem updatedFamem = user.getFamem();
        famemService.update(updatedFamem, famemInfo);
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
        Famem famem = user.getFamem();
        log.info("save new family {}", family);
        famem.setFamily(family);
        famemService.save(famem);
        modelAndView.setViewName("redirect:/account");
        return modelAndView;
    }

    @PostMapping(value = "/addmember")
    public ModelAndView inviteMember(@ModelAttribute("familyInfoMember") @Validated User receiver, BindingResult result,
                                     HttpServletRequest request, RedirectAttributes attributes) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            attributes.addFlashAttribute("result", result);
            modelAndView.setViewName("redirect:/account");
            return modelAndView;
        }
        User initiator = (User) request.getSession().getAttribute("user");
        initiator = userService.findById(initiator.getUserid());
        receiver = userService.findFirstByEmail(receiver.getEmail());
        alertService.setAlert(receiver, initiator);
        attributes.addFlashAttribute("success", "success");
        modelAndView.setViewName("redirect:/account");
        return modelAndView;
    }

    @ModelAttribute
    public void persistUser(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Famem famem = famemService.findById(user.getFamem().getFamemid());
        model.addAttribute("famem", famem);
        if (famem.getFamily() != null) {
            Family family = famem.getFamily();
            model.addAttribute("family", family);
            List<Famem> membersList = family.getFamemList();
            model.addAttribute("membersList", membersList);
            log.info("memberList get {}", membersList);
        }
        Alert alert = alertService.checkForAlerts(famem);
        model.addAttribute("alert", alert);
        if (alert != null) {
            model.addAttribute("invitingFamily", alert.getFamily());
        }
    }

    @InitBinder(value = "familyInfo")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(familyValidator);
    }

    @InitBinder(value = "familyInfoMember")
    private void initEmailBinder(WebDataBinder binder) {
        binder.setValidator(emailValidator);
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

    @Autowired
    public void setAlertService(AlertService alertService) {
        this.alertService = alertService;
    }

    @Autowired
    @Qualifier("emailValidator")
    public void setEmailExistValidator(Validator emailValidator) {
        this.emailValidator = emailValidator;
    }
}
