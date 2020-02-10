package ru.innopolis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.domain.Alert;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.User;
import ru.innopolis.service.AlertService;
import ru.innopolis.service.FamemService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * RegController
 *
 * @author Ekaterina Belolipetskaya
 */
@Controller
@Slf4j
@RequestMapping("/alertproc")
public class AlertProcController {
    private AlertService alertService;
    private FamemService famemService;

    @Autowired
    public AlertProcController(AlertService alertService, FamemService famemService) {
        this.alertService = alertService;
        this.famemService = famemService;
    }

    @PostMapping("/accept")
    public String alertProcAccept(@ModelAttribute("alertInfo") Alert alert, HttpServletRequest request) {
        log.info("alert accepted {}", alert.getAlertid());
        Alert procAlert = alertService.findById(alert.getAlertid());
        procAlert.setIsalertsignproc(BigDecimal.ONE);
        procAlert.setStatus(BigDecimal.ONE);
        alertService.save(procAlert);
        User user = (User) request.getSession().getAttribute("user");
        Famem famem = user.getFamem();
        famem.setFamily(procAlert.getFamily());
        famemService.save(famem);
        return "redirect:/account";
    }

    @PostMapping("/denied")
    public String alertProcDenied(@ModelAttribute("alertInfo") Alert alert) {
        log.info("alert denied {}", alert.getAlertid());
        Alert procAlert = alertService.findById(alert.getAlertid());
        procAlert.setIsalertsignproc(BigDecimal.ONE);
        procAlert.setStatus(BigDecimal.ZERO);
        alertService.save(procAlert);
        return "redirect:/account";
    }
}
