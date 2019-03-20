package fitnessnotebook.hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fitnessnotebook.Urls;
import fitnessnotebook.auth.user.AuthUser;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(Urls.greeting)
    public Greeting greeting() {
        AuthUser user = AuthUser.getCurrentUser();
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, user.getUsername()));
    }
}