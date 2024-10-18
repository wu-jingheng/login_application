package com.wjh.login_application.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {WebMvcConfiguration.class})
class WebMvcConfigurationTest {

    @Autowired
    private WebMvcConfiguration webMvcConfiguration;

    @Test
    void messageSource() {
        // WHEN
        MessageSource messageSource = webMvcConfiguration.messageSource();

        // THEN
        assertNotNull(messageSource);
    }

    @Test
    void localeResolver() {
        // WHEN
        LocaleResolver localeResolver = webMvcConfiguration.localeResolver();

        // THEN
        assertNotNull(localeResolver);
    }

    @Test
    void localeChangeInterceptor() {
        // WHEN
        LocaleChangeInterceptor interceptor = webMvcConfiguration.localeChangeInterceptor();

        // THEN
        assertEquals("lang", interceptor.getParamName());
    }

    @Test
    void addInterceptors() {
        InterceptorRegistry interceptorRegistry = mock(InterceptorRegistry.class);
        assertDoesNotThrow(() -> webMvcConfiguration.addInterceptors(interceptorRegistry));
    }
}