package fitnessnotebook.auth.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fitnessnotebook.Urls;

@RestController
class UserController{
    @GetMapping(Urls.currentUser)
    public HashMap<String, Object> currentUser(HttpServletRequest request, HttpServletResponse response) {

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("user", "anonymousUser");
        return result;
    }
}