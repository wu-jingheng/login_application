package com.wjh.login_application.configuration;

import com.wjh.login_application.handler.LoginFailureHandler;
import com.wjh.login_application.handler.LoginSuccessHandler;
import com.wjh.login_application.utility.RoleKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${app.pages.register.path}")
    private String registerPath;

    @Value("${app.pages.login.path}")
    private String loginPath;

    @Value("${app.pages.home.path}")
    private String homePath;

    @Value("${app.pages.manager-exclusive.path}")
    private String managerExclusivePath;

    @Value("${app.endpoints.register-user}")
    private String registerUserEndpoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   LoginFailureHandler loginFailureHandler,
                                                   LoginSuccessHandler loginSuccessHandler) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("/css/**", loginPath, registerPath, registerUserEndpoint).permitAll()
                        .requestMatchers(homePath).hasAnyRole(
                                RoleKey.defineRoles(List.of(RoleKey.USER, RoleKey.MANAGER, RoleKey.ADMIN))
                        )
                        .requestMatchers(managerExclusivePath, loginPath).hasAnyRole(
                                RoleKey.defineRoles(List.of(RoleKey.MANAGER, RoleKey.ADMIN))
                        )
                        .anyRequest().authenticated())
                .formLogin(configurer -> configurer
                        .loginPage(loginPath)
                        .failureHandler(loginFailureHandler)
                        .successHandler(loginSuccessHandler))
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
