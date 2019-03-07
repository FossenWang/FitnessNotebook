package fitnessnotebook.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.CompositeLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CsrfLogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import fitnessnotebook.auth.user.JpaUserManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/greeting", "/api/auth/login").permitAll()
                // .antMatchers("/", "/home", "/api/auth/login").permitAll()
                // .antMatchers("/greeting").authenticated()
                .and()
            .logout().disable();
            // .csrf().disable();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new JpaUserManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public SessionAuthenticationStrategy sessionStrategy() {
        return new SessionFixationProtectionStrategy();
    }

    @Bean
    public LogoutHandler logoutHandler() {
        LogoutHandler[] logoutHandlers = {
            new CsrfLogoutHandler(new CookieCsrfTokenRepository()),
            new SecurityContextLogoutHandler(),
        };
        return new CompositeLogoutHandler(logoutHandlers);
    }
}