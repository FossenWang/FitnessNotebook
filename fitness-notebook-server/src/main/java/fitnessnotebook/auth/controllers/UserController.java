package fitnessnotebook.auth.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import fitnessnotebook.Urls;
import fitnessnotebook.auth.user.AuthUser;

@RestController
class UserController{

    @GetMapping(Urls.currentUser)
    public AuthUser currentUser(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        AuthUser user;
        if (principal instanceof String) {
            user = AuthUser.createAnonymousUser();
        } else {
            user = (AuthUser) principal;
        }
        return user;
    }
}