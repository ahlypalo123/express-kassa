package com.taviak.expresskassa.service;

import com.taviak.expresskassa.constants.ErrorCode;
import com.taviak.expresskassa.entity.Merchant;
import com.taviak.expresskassa.entity.MerchantDetails;
import com.taviak.expresskassa.exception.ApiException;
import com.taviak.expresskassa.repository.MerchantRepository;
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
      throw new ApiException("Merchant with this email is already exists", ErrorCode.MERCHANT_WITH_THIS_EMAIL_EXISTS);
    });
    merchant.setPassword(bCryptPasswordEncoder.encode(merchant.getPassword()));
    merchantRepository.save(merchant);
  }

  public MerchantDetails updateMerchantDetails(MerchantDetails details, Merchant merchant) {
    if (merchant.getDetails() != null) {
      details.setShift(merchant.getDetails().getShift());
    }
    merchant.setDetails(details);
    return merchantRepository.save(merchant).getDetails();
  }

  public void updatePassword(String email, String password) {
    Merchant merchant = merchantRepository.findOneByEmail(email)
            .orElseThrow(() -> new ApiException("Merchant not found", ErrorCode.MERCHANT_NOT_FOUND));
    merchant.setPassword(bCryptPasswordEncoder.encode(password));
    merchantRepository.save(merchant);
  }

  public MerchantDetails getDetails(Merchant merchant) {
    MerchantDetails details = merchant.getDetails();
    if (details == null) {
      throw new ApiException("Merchant details not found", ErrorCode.MERCHANT_DETAILS_NOT_FOUND);
    }
    return details;
  }

}
