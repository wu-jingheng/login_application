package com.wjh.login_application.controller;

import com.wjh.login_application.model.AppUser;
import com.wjh.login_application.service.AppUserDetailsService;
import com.wjh.login_application.service.SecurityService;
import com.wjh.login_application.utility.DetailProperties;
import com.wjh.login_application.utility.PageKey;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    private final AppUserDetailsService appUserDetailsService;

    private final SecurityService securityService;

    private final PasswordEncoder passwordEncoder;

    private final DetailProperties appDetails;

    public AccountController(AppUserDetailsService appUserDetailsService,
                             SecurityService securityService,
                             PasswordEncoder passwordEncoder,
                             DetailProperties detailProperties) {
        this.appUserDetailsService = appUserDetailsService;
        this.securityService = securityService;
        this.passwordEncoder = passwordEncoder;
        this.appDetails = detailProperties;
    }

    @PostMapping("${app.endpoints.register-user}")
    public String createUser(@ModelAttribute AppUser newAppUser) {
        newAppUser.setPassword(passwordEncoder.encode(newAppUser.getPassword()));
        appUserDetailsService.saveUser(newAppUser);

        String redirectPath = appDetails.getPagePath(PageKey.REGISTER);
        if (securityService.isElevatedAuth()) {
            redirectPath = appDetails.getPagePath(PageKey.MANAGER_EXCLUSIVE);
        }
        redirectPath += "?success=true";

        return "redirect:" + redirectPath;
    }
}
