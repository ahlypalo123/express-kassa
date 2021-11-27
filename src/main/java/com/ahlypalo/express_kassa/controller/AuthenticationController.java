package com.ahlypalo.express_kassa.controller;

import com.ahlypalo.express_kassa.dto.AuthDto;
import com.ahlypalo.express_kassa.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

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
}
