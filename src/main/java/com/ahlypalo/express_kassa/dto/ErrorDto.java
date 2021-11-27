package com.ahlypalo.express_kassa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {
    private String message;
    private int code;
    private String description;
}
