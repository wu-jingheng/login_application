package com.wjh.login_application.controller;

import com.wjh.login_application.service.AppUserDetailsService;
import com.wjh.login_application.service.SecurityService;
import com.wjh.login_application.utility.DetailProperties;
import com.wjh.login_application.utility.PageKey;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ViewControllerTest {

    @Mock
    private AppUserDetailsService appUserDetailsService;

    @Mock
    private SecurityService securityService;

    @Mock
    private DetailProperties appDetails;

    @Mock
    private Model model;

    @InjectMocks
    private ViewController viewController;

    String homePath = "/home";

    String loginView = "login";

    String registerView = "register";

    String managerExclusiveView = "manager";


    @ParameterizedTest
    @CsvSource(delimiterString = " | ", value = {
            "true | redirect:/home",
            "false | login"
    })
    void handleLogin(boolean isUserAuthenticated, String expectedResult) {
        // GIVEN
        when(appDetails.getPagePath(PageKey.HOME)).thenReturn(homePath);
        when(appDetails.getPageView(PageKey.LOGIN)).thenReturn(loginView);
        when(securityService.isUserAuthenticated()).thenReturn(isUserAuthenticated);

        // WHEN
        String result = viewController.handleLogin(model, null, null);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void handleRegister() {
        // GIVEN
        when(appDetails.getPageView(PageKey.REGISTER)).thenReturn(registerView);

        // WHEN
        String result = viewController.handleRegister(model, null, null);

        // THEN
        assertEquals(registerView, result);
    }

    @Test
    void handleManagerExclusive() {
        // GIVEN
        when(appDetails.getPageView(PageKey.MANAGER_EXCLUSIVE)).thenReturn(managerExclusiveView);

        // WHEN
        String result = viewController.handleManagerExclusive(model, null, null);

        // THEN
        assertEquals(managerExclusiveView, result);
    }

    @Test
    void handleAlertMessages_ArgsPresent() {
        // GIVEN
        String error = "present";
        String success = "true";

        // WHEN
        viewController.handleAlertMessages(model, error, success);

        // THEN
        verify(model, times(2)).addAttribute(anyString(), anyString());
    }

    @Test
    void handleAlertMessages_ArgsAbsent() {
        // GIVEN
        String error = null;
        String success = null;

        // WHEN
        viewController.handleAlertMessages(model, error, success);

        // THEN
        verify(model, times(0)).addAttribute(anyString(), anyString());
    }
}