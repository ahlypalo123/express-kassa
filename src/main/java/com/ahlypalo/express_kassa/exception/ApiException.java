package com.ahlypalo.express_kassa.exception;

public class ApiException extends RuntimeException {

  private int code;

  public ApiException(String message, int code) {
    super(message);
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
