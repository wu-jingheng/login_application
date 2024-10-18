package com.wjh.login_application.handler;

import com.wjh.login_application.exception.DuplicateAppUserException;
import com.wjh.login_application.service.SecurityService;
import com.wjh.login_application.utility.DetailProperties;
import com.wjh.login_application.utility.PageKey;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @Mock
    private SecurityService securityService;

    @Mock
    private DetailProperties appDetails;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @ParameterizedTest
    @CsvSource(delimiterString = " | ", value = {
            "true | redirect:null?error=true",
            "false | redirect:/test?error=true"
    })
    void handleDuplicateAppUserException(boolean isElevatedAuth, String expectedRedirect) {
        // GIVEN
        when(appDetails.getPagePath(PageKey.REGISTER)).thenReturn("/test");
        when(securityService.isElevatedAuth()).thenReturn(isElevatedAuth);

        // WHEN
        String redirect = globalExceptionHandler.handleDuplicateAppUserException(
                new DuplicateAppUserException("test"), redirectAttributes
        );

        // THEN
        assertEquals(expectedRedirect, redirect);
    }
}