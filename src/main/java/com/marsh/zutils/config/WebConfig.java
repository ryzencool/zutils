package com.marsh.zutils.config;

import com.marsh.zutils.auth.UserIdentityResolver;
import com.marsh.zutils.helper.JwtHelper;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig {

    private final JwtHelper jwtHelper;

    public WebConfig(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            public void addArgumentResolvers(@NotNull List<HandlerMethodArgumentResolver> resolvers) {
                resolvers.add(new UserIdentityResolver(jwtHelper));
            }
        };
    }

}
