package com.ntthuat.auth.controller;

import com.ntthuat.auth.AuthenticationApplication;
import com.ntthuat.auth.config.SecurityConfig;
import com.ntthuat.auth.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

/**
 * @author ntthuat
 */
@SpringBootTest(
        classes = {AuthenticationApplication.class}
        , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {SecurityConfig.class})
@DisplayName("Auth API Test")
class AuthControllerTest {

    @Inject
    private WebApplicationContext context;

    @Inject
    MockMvc mvc;

    @Inject
    private Filter springSecurityFilterChain;

    @LocalServerPort
    int randomServerPort;

    @Inject
    UserRepository userRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    void testPostSignIn() throws Exception {
        final String baseUrl = "http://localhost:" + randomServerPort + "/auth/signin";
        mvc

                .perform(post(baseUrl).param("username", "admin").param("password", "password"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void testPostSignUp() throws Exception {
        assertFalse(userRepository.existsByUserName("newUser"));

        final String baseUrl = "http://localhost:" + randomServerPort + "/auth/signup";
        mvc

                .perform(post(baseUrl).param("username", "newUser").param("password", "newPassword"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        assertTrue(userRepository.existsByUserName("newUser"));

    }

}
