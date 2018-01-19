package com.sagarandcompany.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MyWebConfig.class)
public class RegistrationControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void setup () {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    @Test
    public void testLogin () throws Exception {

        MockHttpServletRequestBuilder builder =
                            MockMvcRequestBuilders.post("/login")
                                                  .param("emailAddress",
                                                         "admin@exmaple.com")
                                                  .param("password", "password");
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk());
    }

    @Test
    public void testPostLogin() throws Exception{
        MockHttpSession session = new MockHttpSession();

        User user = new User();
        user.setEmailAddress("admin@example.com");
        user.setPassword("password");
        user = userService.loginUser(user);

        session.setAttribute("user", user);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/app")
                                        .session(session);
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk());

    }
    
}