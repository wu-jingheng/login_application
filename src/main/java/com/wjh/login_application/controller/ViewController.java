package com.wjh.login_application.controller;

import com.wjh.login_application.model.AppUserDto;
import com.wjh.login_application.service.AppUserDetailsService;
import com.wjh.login_application.service.SecurityService;
import com.wjh.login_application.utility.DetailProperties;
import com.wjh.login_application.utility.PageKey;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final AppUserDetailsService appUserDetailsService;

    private final SecurityService securityService;

    private final DetailProperties appDetails;

    public ViewController(AppUserDetailsService appUserDetailsService,
                          SecurityService securityService,
                          DetailProperties detailProperties) {
        this.appUserDetailsService = appUserDetailsService;
        this.securityService = securityService;
        this.appDetails = detailProperties;
    }

    @GetMapping({"${app.pages.root.path}", "${app.pages.login.path}"})
    public String handleLogin(Model model, String error, String logout) {
        String pagePath = appDetails.getPagePath(PageKey.HOME);
        String pathView = appDetails.getPageView(PageKey.LOGIN);

        if (securityService.isUserAuthenticated()) {
            return "redirect:" + pagePath;
        }
        handleAlertMessages(model, error, logout);

        return pathView;
    }

    @GetMapping("${app.pages.register.path}")
    public String handleRegister(Model model, String error, String success) {
        handleAlertMessages(model, error, success);
        return appDetails.getPageView(PageKey.REGISTER);
    }

    @GetMapping("${app.pages.home.path}")
    public String handleUserHome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        AppUserDto appUserDto = appUserDetailsService.getAppUserDtoByUsername(user.getUsername());
        model.addAttribute("appUserDto", appUserDto);
        return appDetails.getPageView(PageKey.HOME);
    }

    @GetMapping("${app.pages.manager-exclusive.path}")
    public String handleManagerExclusive(Model model, String error, String success) {
        handleAlertMessages(model, error, success);
        return appDetails.getPageView(PageKey.MANAGER_EXCLUSIVE);
    }

    // Helper methods

    public void handleAlertMessages(Model model, String error, String success) {
        if (error != null) {
            model.addAttribute("error", "present");
        }
        if (success != null) {
            model.addAttribute("success", "true");
        }
    }
}
