package com.ahlypalo.express_kassa.controller;

import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.ReceiptTemplate;
import com.ahlypalo.express_kassa.repository.ReceiptTemplateRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReceiptTemplateController {

    private final ReceiptTemplateRepository receiptTemplateRepository;

    public ReceiptTemplateController(ReceiptTemplateRepository receiptTemplateRepository) {
        this.receiptTemplateRepository = receiptTemplateRepository;
    }

    @PostMapping("/receipt-template")
    public void saveReceiptTemplate(@RequestBody ReceiptTemplate receiptTemplate, Merchant merchant) {
        receiptTemplate.setMerchant(merchant);
        receiptTemplateRepository.save(receiptTemplate);
    }

    @GetMapping("/receipt-template")
    public List<ReceiptTemplate> getReceiptTemplates(Merchant merchant) {
        return receiptTemplateRepository.findWhereMerchantIdAndMerchantIdIsNull(merchant.getId());
    }
}
