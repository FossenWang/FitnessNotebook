package fitnessnotebook.auth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import fitnessnotebook.Urls;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class AuthTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private UserDetailsManager userManager;

    @Value("${spring.profiles.active:}")
    private String activeProfiles;

    private String username = "fossen";
    private String password = "password";
    
    @Autowired
    private ObjectMapper jsonMapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();

        UserDetails user = User.builder()
            .username(username)
            .password(password)
            .roles("USER").build();
        userManager.createUser(user);
    }

    @Test
    public void testLoginAndLogout() throws Exception {
        MvcResult result;

        // Not login
        result = this.mockMvc
            .perform(get(Urls.currentUser))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").isEmpty())
            .andExpect(jsonPath("$.username").value("anonymousUser"))
            .andReturn();

        // Login
        result = this.mockMvc
            .perform(post(Urls.login)
            .with(csrf().asHeader()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.login").value(true))
            .andExpect(jsonPath("$.user.id").isNumber())
            .andExpect(jsonPath("$.user.username").value(username))
            .andReturn();

        // Save the session for the following use
        MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
        // Exact user id
        JsonNode jNode = jsonMapper.readTree(result.getResponse().getContentAsString());
        Integer userId = jNode.get("user").get("id").asInt();

        // Get currentUser
        result = this.mockMvc
            .perform(get(Urls.currentUser)
            .session(session))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(userId))
            .andExpect(jsonPath("$.username").value(username))
            .andReturn();

        // Logout
        result = this.mockMvc
            .perform(post(Urls.logout)
            .with(csrf().asHeader())
            .session(session))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.logout").value(true))
            .andExpect(jsonPath("$.user.id").isEmpty())
            .andExpect(jsonPath("$.user.username").value("anonymousUser"))
            .andReturn();
        
        // Session will be freshed after logout and login
        session = (MockHttpSession) result.getRequest().getSession();

        // Not Login, check logout indeed
        result = this.mockMvc
        .perform(get(Urls.currentUser)
        .session(session))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").isEmpty())
        .andExpect(jsonPath("$.username").value("anonymousUser"))
        .andReturn();
    }
}