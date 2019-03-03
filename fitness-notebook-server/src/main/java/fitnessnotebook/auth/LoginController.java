package fitnessnotebook.auth;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import fitnessnotebook.Urls;

@RestController
public class LoginController {

    @Autowired
	private AuthenticationManager authenticationManager;

    // fetch('/api/auth/login', {
    //     method: 'POST'
    // }).then(response => response.json()).then(data => console.log(data))
    // @PostMapping("/api/auth/login")
    @PostMapping(Urls.login)
    public HashMap<String, Object> login(HttpServletRequest request) {

        String username = "fossen", password="password";
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
            username, password);
        authRequest.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        Authentication authResult = authenticationManager.authenticate(authRequest);
        
        SecurityContextHolder.getContext().setAuthentication(authResult);

        UserDetails user = (UserDetails)authResult.getPrincipal();
        System.out.println(user);
        // SecurityContext context = SecurityContextHolder.getContext();
        // System.out.println(context);
        // HttpSession session = request.getSession();
        // System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT"));

        HashMap<String, Object> result = new HashMap<String, Object>();

        result.put("login", true);
        result.put("user", user);
        return result;
    }
}