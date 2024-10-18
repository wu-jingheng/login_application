package com.wjh.login_application.handler;

import com.wjh.login_application.utility.DetailProperties;
import com.wjh.login_application.utility.PageKey;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Generated;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Generated
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private final DetailProperties appDetails;

    public LoginFailureHandler(DetailProperties detailProperties) {
        this.appDetails = detailProperties;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.sendRedirect(appDetails.getPagePath(PageKey.LOGIN) + "?error=true");
    }
}
