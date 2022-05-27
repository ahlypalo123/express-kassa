package com.ahlypalo.express_kassa.exception;

import com.ahlypalo.express_kassa.dto.ErrorDto;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static com.ahlypalo.express_kassa.constants.ErrorCode.UNKNOWN_ERROR;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    @Order(1)
    protected ResponseEntity<ErrorDto> handleApiException(ApiException ex, HttpServletRequest request) {
        ErrorDto error = new ErrorDto(ex.getMessage(), ex.getCode());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    @Order(2)
    protected ResponseEntity<ErrorDto> handleException(Exception ex, HttpServletRequest request) {
        ErrorDto error = new ErrorDto(ex.getMessage(), UNKNOWN_ERROR);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
