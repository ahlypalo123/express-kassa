package com.taviak.expresskassa.controller;

import com.taviak.expresskassa.dto.AuthDto;
import com.taviak.expresskassa.entity.Merchant;
import com.taviak.expresskassa.entity.MerchantDetails;
import com.taviak.expresskassa.service.MerchantService;
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
