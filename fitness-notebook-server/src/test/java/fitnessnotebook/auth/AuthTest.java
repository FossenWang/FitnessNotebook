package fitnessnotebook.auth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    public void testLogin() throws Exception {
        MvcResult result = this.mockMvc
            .perform(post(Urls.login)
            .with(csrf().asHeader()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.login").value(true))
            .andExpect(jsonPath("$.user.username").value("fossen"))
            .andExpect(jsonPath("$.user.password").isEmpty())
            .andReturn();

        MockHttpSession session = (MockHttpSession) result.getRequest().getSession();

        result = this.mockMvc
            .perform(post(Urls.logout)
            .with(csrf().asHeader())
            .session(session))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.logout").value(true))
            .andExpect(jsonPath("$.user").value("anonymousUser"))
            .andReturn();
    }
}