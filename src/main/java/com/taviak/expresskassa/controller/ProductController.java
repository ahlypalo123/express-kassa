package com.taviak.expresskassa.controller;

import com.taviak.expresskassa.config.SwaggerConfig;
import com.taviak.expresskassa.entity.Merchant;
import com.taviak.expresskassa.entity.Product;
import com.taviak.expresskassa.repository.ProductRepository;
import com.taviak.expresskassa.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Api(tags = SwaggerConfig.TAG_PRODUCT)
public class ProductController {

  private static final String PARAM_BAR_CODE = "bar_code";

  private final ProductRepository productRepository;
  private final ProductService productService;

  public ProductController(ProductRepository productRepository, ProductService productService) {
    this.productRepository = productRepository;
    this.productService = productService;
  }

  @GetMapping
  @ApiOperation("Получение списка товаров")
  public Iterable<Product> getProducts(Merchant merchant,
          @RequestParam(required = false) String name,
          @RequestParam(name = PARAM_BAR_CODE, required = false) String barCode) {
    return productService.findAllByMerchant(merchant, name, barCode);
  }

  @PostMapping
  @ApiOperation("Добавление")
  public void addProduct(@RequestBody Product product, Merchant merchant) {
    productService.saveProduct(product, merchant);
  }

  @DeleteMapping("/{id}")
  @ApiOperation("Удаление")
  public void deleteProduct(@PathVariable Long id, Merchant merchant) {
    productRepository.deleteByIdAndMerchant(id, merchant);
  }

}
