package com.wjh.login_application.handler;

import com.wjh.login_application.utility.DetailProperties;
import com.wjh.login_application.utility.PageKey;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Generated;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Generated
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final DetailProperties appDetails;

    public LoginSuccessHandler(DetailProperties detailProperties) {
        this.appDetails = detailProperties;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.sendRedirect(appDetails.getPagePath(PageKey.HOME));
    }
}
