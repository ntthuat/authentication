package com.ntthuat.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

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
    void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    void testSignIn() throws Exception {
        final String baseUrl = "http://localhost:" + randomServerPort + "/auth/signin";
        mvc
                .perform(post(baseUrl).param("username", "ntthuat1").param("password", "password"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void testSignUp() throws Exception {
        assertFalse(userRepository.existsByUserName("newUser"));

        final String baseUrl = "http://localhost:" + randomServerPort + "/auth/signup";
        mvc

                .perform(post(baseUrl)
                        .param("userName", "newUser")
                        .param("password", "newPassword")
                        .param("email", "newEmail@gmail.com")
                        .param("firstName", "newFirstName")
                        .param("lastName", "newLastName"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        assertTrue(userRepository.existsByUserName("newUser"));

    }

    @Test
    void testSignOut() throws Exception {
        final String signInUrl = "http://localhost:" + randomServerPort + "/auth/signin";
        MvcResult result = mvc
                .perform(post(signInUrl)
                        .param("username", "ntthuat1")
                        .param("password", "password"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        final String bearerToken = result.getResponse().getContentAsString();
        Map map = new ObjectMapper().readValue(bearerToken, Map.class);
        final String jwt = (String) map.get("accessToken");

        final String signOutUrl = "http://localhost:" + randomServerPort + "/auth/signout";
        mvc
                .perform(post(signOutUrl)
                        .header("Authorization", "Bearer " + jwt)
                )
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }


}
