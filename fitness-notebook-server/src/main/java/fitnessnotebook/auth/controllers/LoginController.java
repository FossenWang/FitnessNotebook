package fitnessnotebook.auth.controllers;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import fitnessnotebook.Urls;
import fitnessnotebook.auth.user.AuthUser;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SessionAuthenticationStrategy sessionStrategy;

    // fetch('/api/auth/login', {
    //     method: 'POST', credentials: 'include'
    // }).then(response => response.json()).then(data => console.log(data))
    @PostMapping(Urls.login)
    public HashMap<String, Object> login(HttpServletRequest request, HttpServletResponse response) {

        String username = "fossen", password="password";
        UsernamePasswordAuthenticationToken authRequest =
            new UsernamePasswordAuthenticationToken(username, password);
        authRequest.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        Authentication authResult = authenticationManager.authenticate(authRequest);

        sessionStrategy.onAuthentication(authResult, request, response);
        SecurityContextHolder.getContext().setAuthentication(authResult);

        AuthUser user = (AuthUser) authResult.getPrincipal();
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("login", true);
        result.put("user", user);
        return result;
    }
}