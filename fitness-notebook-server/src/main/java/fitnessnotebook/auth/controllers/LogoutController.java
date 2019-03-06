package fitnessnotebook.auth.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import fitnessnotebook.Urls;

@RestController
public class LogoutController {

    @Autowired
    private LogoutHandler logoutHandler;

    @PostMapping(Urls.logout)
    public HashMap<String, Object> logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logoutHandler.logout(request, response, authentication);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("logout", true);
        result.put("user", "anonymousUser");
        return result;
    }
}