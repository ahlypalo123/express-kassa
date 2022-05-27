package com.ahlypalo.express_kassa.service;

import com.ahlypalo.express_kassa.constants.ErrorCode;
import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.MerchantDetails;
import com.ahlypalo.express_kassa.exception.ApiException;
import com.ahlypalo.express_kassa.repository.MerchantDetailsRepository;
import com.ahlypalo.express_kassa.repository.MerchantRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

  private final MerchantRepository merchantRepository;
  private final MerchantDetailsRepository merchantDetailsRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public MerchantService(MerchantRepository merchantRepository, MerchantDetailsRepository merchantDetailsRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.merchantRepository = merchantRepository;
    this.merchantDetailsRepository = merchantDetailsRepository;
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
      details.setId(merchant.getDetails().getId());
      details.setShift(merchant.getDetails().getShift());
    }
    MerchantDetails res = merchantDetailsRepository.save(details);
    if (merchant.getDetails() == null) {
      merchant.setDetails(res);
      merchantRepository.save(merchant);
    }
    return res;
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
