package com.wjh.login_application.handler;

import com.wjh.login_application.exception.DuplicateAppUserException;
import com.wjh.login_application.service.SecurityService;
import com.wjh.login_application.utility.DetailProperties;
import com.wjh.login_application.utility.PageKey;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final SecurityService securityService;

    private final DetailProperties appDetails;

    public GlobalExceptionHandler(SecurityService securityService, DetailProperties detailProperties) {
        this.securityService = securityService;
        this.appDetails = detailProperties;
    }

    @ExceptionHandler(DuplicateAppUserException.class)
    public String handleDuplicateAppUserException(DuplicateAppUserException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());

        String redirectPath = appDetails.getPagePath(PageKey.REGISTER);
        if (securityService.isElevatedAuth()) {
            redirectPath = appDetails.getPagePath(PageKey.MANAGER_EXCLUSIVE);
        }
        redirectPath += "?error=true";

        return "redirect:" + redirectPath;
    }
}
