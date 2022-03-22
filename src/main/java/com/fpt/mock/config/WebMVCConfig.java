package com.fpt.mock.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    private final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"};

    @Value("${fpt.mock.externalResources}")
    private String externalResources;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        List<String> resources = new ArrayList<>(List.of(CLASSPATH_RESOURCE_LOCATIONS));
        resources.add(externalResources);
        registry.addResourceHandler("/**").addResourceLocations(resources.toArray(String[]::new));
    }
}
