package ru.innopolis.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import ru.innopolis.domain.User;
import ru.innopolis.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * SaveUserInSessionHandler.
 */
@Slf4j
public class SaveUserInSessionHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        String targetUrl = null;
        if (savedRequest != null) {
            targetUrl = savedRequest.getRedirectUrl();
        }

        clearSession(request);
        User user;
        try {
            user = userService.findFirstByLogin(authentication.getName());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.sendRedirect("/login");
            return;
        }

        log.info("save user to session {}", user);
        request.getSession().setAttribute("user", user);

        if (targetUrl != null) {
            response.sendRedirect(targetUrl);
        } else {
            response.sendRedirect("/account");
        }
    }

    private void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.invalidate();
    }
}
