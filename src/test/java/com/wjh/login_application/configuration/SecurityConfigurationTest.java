package com.wjh.login_application.configuration;

import com.wjh.login_application.handler.LoginFailureHandler;
import com.wjh.login_application.handler.LoginSuccessHandler;
import com.wjh.login_application.service.AppUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SecurityConfigurationTest {

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private HttpSecurity httpSecurity;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Test
    void securityFilterChain() throws Exception {
        // WHEN
        SecurityFilterChain securityFilterChain = securityConfiguration.securityFilterChain(httpSecurity, loginFailureHandler, loginSuccessHandler);

        // THEN
        assertNotNull(securityFilterChain);
    }

    @Test
    void authenticationProvider() {
        // WHEN
        AuthenticationProvider authenticationProvider = securityConfiguration.authenticationProvider(appUserDetailsService);

        // THEN
        assertNotNull(authenticationProvider);
    }

    @Test
    void passwordEncoder() {
        // WHEN
        Object passwordEncoder = securityConfiguration.passwordEncoder();

        // THEN
        assertInstanceOf(BCryptPasswordEncoder.class, passwordEncoder);
    }
}
