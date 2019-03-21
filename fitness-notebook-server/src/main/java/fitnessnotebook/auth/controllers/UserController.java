package fitnessnotebook.auth.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fitnessnotebook.Urls;
import fitnessnotebook.auth.dao.AuthUser;

@RestController
public class UserController{

    @GetMapping(Urls.currentUser)
    public AuthUser currentUser() {
        AuthUser user = AuthUser.getCurrentUser();
        return user;
    }

    public void register() {
        
    }
}