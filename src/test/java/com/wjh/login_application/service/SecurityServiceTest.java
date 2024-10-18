package com.wjh.login_application.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityServiceTest {

    @Autowired
    private SecurityService securityService;

    @Test
    @WithMockUser(roles = {"MANAGER"})
    void isElevatedAuth_Manager() {
        // WHEN
        boolean result = securityService.isElevatedAuth();

        // THEN
        assertTrue(result);
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void isElevatedAuth_Admin() {
        // WHEN
        boolean result = securityService.isElevatedAuth();

        // THEN
        assertTrue(result);
    }

    @Test
    @WithMockUser
    void isElevatedAuth_User() {
        // WHEN
        boolean result = securityService.isElevatedAuth();

        // THEN
        assertFalse(result);
    }

    @Test
    @WithMockUser
    void isUserAuthenticated_True() {
        // WHEN
        boolean result = securityService.isUserAuthenticated();

        // THEN
        assertTrue(result);
    }

    @Test
    void isUserAuthenticated_False() {
        // WHEN
        boolean result = securityService.isUserAuthenticated();

        // THEN
        assertFalse(result);
    }
}