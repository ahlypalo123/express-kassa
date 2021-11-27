package com.ahlypalo.express_kassa.controller;

import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.MerchantDetails;
import com.ahlypalo.express_kassa.service.MerchantService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

  private final MerchantService merchantService;

  public MerchantController(MerchantService merchantService) {
    this.merchantService = merchantService;
  }

  @GetMapping
  public Merchant getMe(Merchant merchant) {
    return merchant;
  }

  @PutMapping
  public void updateMerchantDetails(Merchant merchant, @RequestBody MerchantDetails details) {
    merchantService.updateMerchantDetails(details, merchant);
  }
}
