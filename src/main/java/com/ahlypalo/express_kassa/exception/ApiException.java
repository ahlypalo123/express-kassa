package com.ahlypalo.express_kassa.exception;

public class ApiException extends RuntimeException {

  public ApiException(String message) {
    super(message);
  }
}
