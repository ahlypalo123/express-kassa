package com.taviak.expresskassa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    public static final String TAG_AUTHENTICATION = "authentication";
    public static final String TAG_CHECK = "check";
    public static final String TAG_MERCHANT = "merchant";
    public static final String TAG_PHOTO = "photo";
    public static final String TAG_PRODUCT = "product";
    public static final String TAG_SHIFT = "shift";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.taviak.expresskassa"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(TAG_AUTHENTICATION, "Авторизация и регистрация пользователя в Api"),
                        new Tag(TAG_CHECK, "Чеки"),
                        new Tag(TAG_MERCHANT, "Данные о магазине"),
                        new Tag(TAG_PHOTO, "Изображения"),
                        new Tag(TAG_PRODUCT, "Товары"),
                        new Tag(TAG_SHIFT, "Смена"));
    }
}
