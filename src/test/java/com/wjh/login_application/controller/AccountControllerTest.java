package com.wjh.login_application.controller;

import com.wjh.login_application.model.AppUser;
import com.wjh.login_application.service.AppUserDetailsService;
import com.wjh.login_application.service.SecurityService;
import com.wjh.login_application.utility.DetailProperties;
import com.wjh.login_application.utility.PageKey;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private AppUserDetailsService appUserDetailsService;

    @Mock
    private SecurityService securityService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private DetailProperties appDetails;

    @InjectMocks
    private AccountController accountController;

    @ParameterizedTest
    @CsvSource(delimiterString = " | ", value = {
            "true | redirect:null?success=true",
            "false | redirect:/register?success=true"
    })
    void createUser(boolean isElevatedAuth, String expectedRedirect) {
        // GIVEN
        AppUser appUser = new AppUser();
        appUser.setName("Test name");
        appUser.setUsername("Test_username");
        appUser.setPassword("Test_password");
        appUser.setRole("user");

        doNothing().when(appUserDetailsService).saveUser(any(AppUser.class));
        when(securityService.isElevatedAuth()).thenReturn(isElevatedAuth);
        when(appDetails.getPagePath(PageKey.REGISTER)).thenReturn("/register");

        // WHEN
        String redirectPath = accountController.createUser(appUser);

        // THEN
        assertEquals(expectedRedirect, redirectPath);
    }
}