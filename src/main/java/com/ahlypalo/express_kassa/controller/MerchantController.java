package com.ahlypalo.express_kassa.controller;

import com.ahlypalo.express_kassa.dto.AuthDto;
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
  public MerchantDetails getDetails(Merchant merchant) {
    return merchantService.getDetails(merchant);
  }

  @PutMapping
  public MerchantDetails updateMerchantDetails(Merchant merchant, @RequestBody MerchantDetails details) {
    return merchantService.updateMerchantDetails(details, merchant);
  }

  @PutMapping("/update-password")
  public void updatePassword(@RequestBody AuthDto req) {
    merchantService.updatePassword(req.getEmail(), req.getPassword());
  }

}
