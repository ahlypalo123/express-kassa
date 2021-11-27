package com.ahlypalo.express_kassa.service;

import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.MerchantDetails;
import com.ahlypalo.express_kassa.exception.ApiException;
import com.ahlypalo.express_kassa.repository.MerchantRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

  private final MerchantRepository merchantRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public MerchantService(MerchantRepository merchantRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.merchantRepository = merchantRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  public void saveMerchant(Merchant merchant) {
    merchantRepository.findOneByEmail(merchant.getEmail()).ifPresent(e -> {
      throw new ApiException("Merchant with this email is already exists");
    });
    merchant.setPassword(bCryptPasswordEncoder.encode(merchant.getPassword()));
    merchantRepository.save(merchant);
  }

  public void updateMerchantDetails(MerchantDetails details, Merchant merchant) {
    merchant.setDetails(details);
    merchantRepository.save(merchant);
  }

}
