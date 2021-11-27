package com.ahlypalo.express_kassa.config;

import com.ahlypalo.express_kassa.resolver.MerchantAuthenticationSubjectResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AuthParamsConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MerchantAuthenticationSubjectResolver());
    }
}
