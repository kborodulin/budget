package ru.innopolis.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ru.innopolis.domain.Alert;
import ru.innopolis.domain.Famem;
import ru.innopolis.domain.User;
import ru.innopolis.service.AlertService;
import ru.innopolis.service.FamemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AlertInterceptor extends HandlerInterceptorAdapter {
    FamemService famemService;
    AlertService alertService;

    @Autowired
    public AlertInterceptor(FamemService famemService, AlertService alertService) {
        this.famemService = famemService;
        this.alertService = alertService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) return;
        Famem famem = famemService.findById(user.getFamem().getFamemid());
        Alert alert = alertService.checkForAlerts(famem);
        modelAndView.addObject("alert", alert);
        if (alert != null) {
            modelAndView.addObject("invitingFamily", alert.getFamily());
        }
    }
}
