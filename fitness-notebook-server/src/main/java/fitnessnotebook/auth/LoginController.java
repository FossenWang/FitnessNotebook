package fitnessnotebook.auth;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fitnessnotebook.Urls;

@RestController
public class LoginController {

    // @PostMapping("/api/auth/login")
    @PostMapping(Urls.login)
    public HashMap<String, Boolean> login(HttpServletRequest request) {
        System.out.println(request);
        HashMap<String, Boolean> result = new HashMap<String, Boolean>();
        result.put("login", true);
        return result;
    }
}
