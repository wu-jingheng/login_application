package com.wjh.login_application.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class ViewControllerMvcTest {

    @Mock
    private Model model;

    @Autowired
    private ViewController viewController;

    @Value("${app.pages.home.view}")
    private String expectedView;

    @Test
    @WithMockUser(username = "user")
    void handleUserHome() {
        // WHEN
        String result = viewController.handleUserHome(model);

        // THEN
        assertEquals(expectedView, result);
    }
}