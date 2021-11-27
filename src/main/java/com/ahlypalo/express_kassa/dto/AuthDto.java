package com.ahlypalo.express_kassa.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class AuthDto {
    private String email;
    private String password;
}
