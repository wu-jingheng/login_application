package com.wjh.login_application.utility;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@Component
@Generated
@ConfigurationProperties(prefix = "app")
public class DetailProperties {

    private Map<String, Page> pages;
    private Map<String, String> endpoints;

    @Getter
    @Setter
    @Generated
    public static class Page {
        private String view;
        private String path;
    }

    public Page getPage(PageKey key) {
        return pages.get(key.getValue());
    }

    public String getPagePath(PageKey key) {
        return pages.get(key.getValue()).getPath();
    }

    public String getPageView(PageKey key) {
        return pages.get(key.getValue()).getView();
    }

    public String getEndpoint(EndpointKey key) {
        return endpoints.get(key.getValue());
    }
}
