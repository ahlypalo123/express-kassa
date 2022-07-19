package com.taviak.expresskassa.controller;

import com.taviak.expresskassa.config.SwaggerConfig;
import com.taviak.expresskassa.dto.AuthDto;
import com.taviak.expresskassa.dto.ValidationDto;
import com.taviak.expresskassa.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/auth")
@Api(tags = SwaggerConfig.TAG_AUTHENTICATION)
public class AuthenticationController {

    private static final String KEY_EMAIL = "email";

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @ApiOperation("Авторизация")
    public String login(@RequestBody AuthDto dto) {
        return authenticationService.login(dto);
    }

    @PostMapping("/register")
    @ApiOperation("Регистрация")
    public void register(@RequestBody AuthDto dto) {
        authenticationService.register(dto);
    }

    @PostMapping("/forgot-password")
    @ApiOperation("Восстановление пароля")
    public void forgotPassword(@RequestBody HashMap<String, String> req) throws ExecutionException {
        String email = req.get(KEY_EMAIL);
        authenticationService.sendConfirmationCode(email);
    }

    @PostMapping("/validate")
    @ApiOperation("Проверка кода подтверждения")
    public void validateCode(@RequestBody ValidationDto req) throws ExecutionException {
        authenticationService.validateCode(req.getEmail(), req.getCode());
    }
}
