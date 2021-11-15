package com.ahlypalo.express_kassa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

  @GetMapping("/")
  public String check() {
    return "Hello!";
  }

}
