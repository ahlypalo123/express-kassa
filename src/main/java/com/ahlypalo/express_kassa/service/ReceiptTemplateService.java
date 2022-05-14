package com.ahlypalo.express_kassa.service;

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

    public void addReceiptTemplate(ReceiptTemplate receiptTemplate, Merchant merchant) {
        receiptTemplate.setMerchant(merchant);
        receiptTemplateRepository.save(receiptTemplate);
    }

    public ReceiptTemplate getActive(Merchant merchant) {
        return receiptTemplateRepository.findByIsActiveIsTrueAndMerchant(merchant)
                .orElseThrow(() -> new ApiException("Receipt not found"));
    }

    public void setActiveTemplate(Long id, Merchant merchant) {
        Optional<ReceiptTemplate> rtOpt = receiptTemplateRepository.findByIsActiveIsTrueAndMerchant(merchant);
        ReceiptTemplate rt;
        if (rtOpt.isPresent()) {
            rt = rtOpt.get();
            rt.setActive(false);
            receiptTemplateRepository.save(rt);
        }
        rt = receiptTemplateRepository.findByIdAndMerchant(id, merchant)
                .orElseThrow(() -> new ApiException("Receipt not found"));
        rt.setActive(true);
        receiptTemplateRepository.save(rt);
    }
}
