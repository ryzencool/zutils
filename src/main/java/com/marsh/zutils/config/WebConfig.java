package com.marsh.zutils.config;

import com.marsh.zutils.auth.UserIdentityResolver;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            public void addArgumentResolvers(@NotNull List<HandlerMethodArgumentResolver> resolvers) {
                resolvers.add(new UserIdentityResolver());
            }
        };
    }

}
