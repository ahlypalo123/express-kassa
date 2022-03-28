package com.ahlypalo.express_kassa.controller;

import com.ahlypalo.express_kassa.dto.AuthDto;
import com.ahlypalo.express_kassa.dto.ValidationDto;
import com.ahlypalo.express_kassa.service.AuthenticationService;
import com.ahlypalo.express_kassa.service.MerchantService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private static final String KEY_EMAIL = "email";

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthDto dto) {
        return authenticationService.login(dto);
    }

    @PostMapping("/register")
    public void register(@RequestBody AuthDto dto) {
        authenticationService.register(dto);
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody HashMap<String, String> req) throws ExecutionException {
        String email = req.get(KEY_EMAIL);
        authenticationService.sendConfirmationCode(email);
    }

    @PostMapping("/validate")
    public void validateCode(@RequestBody ValidationDto req) throws ExecutionException {
        authenticationService.validateCode(req.getEmail(), req.getCode());
    }
}
