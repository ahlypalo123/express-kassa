package com.ahlypalo.express_kassa.service;

import com.ahlypalo.express_kassa.constants.ErrorCode;
import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.ReceiptTemplate;
import com.ahlypalo.express_kassa.exception.ApiException;
import com.ahlypalo.express_kassa.repository.ReceiptTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReceiptTemplateService {

    private final ReceiptTemplateRepository receiptTemplateRepository;

    public ReceiptTemplateService(ReceiptTemplateRepository receiptTemplateRepository) {
        this.receiptTemplateRepository = receiptTemplateRepository;
    }

    public ReceiptTemplate addReceiptTemplate(ReceiptTemplate receiptTemplate, Merchant merchant) {
        receiptTemplate.setMerchant(merchant);
        if (receiptTemplate.isActive()) {
            deactivateCurrent(merchant);
        }
        return receiptTemplateRepository.save(receiptTemplate);
    }

    public ReceiptTemplate getActive(Merchant merchant) {
        return receiptTemplateRepository.findByIsActiveIsTrueAndMerchant(merchant)
                .orElseThrow(() -> new ApiException("Receipt not found", ErrorCode.RECEIPT_NOT_FOUND));
    }

    public void deactivateCurrent(Merchant merchant) {
        Optional<ReceiptTemplate> rtOpt = receiptTemplateRepository.findByIsActiveIsTrueAndMerchant(merchant);
        ReceiptTemplate rt;
        if (rtOpt.isPresent()) {
            rt = rtOpt.get();
            rt.setActive(false);
            receiptTemplateRepository.save(rt);
        }
    }

    public void setActiveTemplate(Long id, Merchant merchant) {
        deactivateCurrent(merchant);
        ReceiptTemplate rt = receiptTemplateRepository.findByIdAndMerchant(id, merchant)
                .orElseThrow(() -> new ApiException("Receipt not found", ErrorCode.RECEIPT_NOT_FOUND));
        rt.setActive(true);
        receiptTemplateRepository.save(rt);
    }
}
