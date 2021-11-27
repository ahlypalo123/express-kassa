package com.ahlypalo.express_kassa.controller;

import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.Product;
import com.ahlypalo.express_kassa.repository.ProductRepository;
import com.ahlypalo.express_kassa.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

  private static final String PARAM_BAR_CODE = "bar_code";

  private final ProductRepository productRepository;
  private final ProductService productService;

  public ProductController(ProductRepository productRepository, ProductService productService) {
    this.productRepository = productRepository;
    this.productService = productService;
  }

  @GetMapping
  public Iterable<Product> getProducts(Merchant merchant,
          @RequestParam(required = false) String name,
          @RequestParam(name = PARAM_BAR_CODE, required = false) String barCode) {
    return productService.findAllByMerchant(merchant, name, barCode);
  }

  @PostMapping
  public void addProduct(@RequestBody Product product, Merchant merchant) {
    productService.saveProduct(product, merchant);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable Long id, Merchant merchant) {
    productRepository.deleteByIdAndMerchant(id, merchant);
  }

}
