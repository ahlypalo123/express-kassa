package com.taviak.expresskassa.controller;

import com.taviak.expresskassa.config.SwaggerConfig;
import com.taviak.expresskassa.dto.AuthDto;
import com.taviak.expresskassa.entity.Merchant;
import com.taviak.expresskassa.entity.MerchantDetails;
import com.taviak.expresskassa.service.MerchantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merchant")
@Api(tags = SwaggerConfig.TAG_MERCHANT)
public class MerchantController {

  private final MerchantService merchantService;

  public MerchantController(MerchantService merchantService) {
    this.merchantService = merchantService;
  }

  @GetMapping
  @ApiOperation("Получить информацию о текущем магазине")
  public MerchantDetails getDetails(Merchant merchant) {
    return merchantService.getDetails(merchant);
  }

  @PutMapping
  @ApiOperation("Обновление деталей магазина")
  public MerchantDetails updateMerchantDetails(Merchant merchant, @RequestBody MerchantDetails details) {
    return merchantService.updateMerchantDetails(details, merchant);
  }

  @PutMapping("/update-password")
  @ApiOperation("Обновление пароля")
  public void updatePassword(@RequestBody AuthDto req) {
    merchantService.updatePassword(req.getEmail(), req.getPassword());
  }

}
