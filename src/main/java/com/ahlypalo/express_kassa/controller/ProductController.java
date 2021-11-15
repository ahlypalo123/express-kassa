package com.ahlypalo.express_kassa.controller;

import com.ahlypalo.express_kassa.model.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/product")
public class ProductController {

  @PostMapping
  public void addProduct(Product product) {

  }

}
