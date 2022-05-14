package com.ahlypalo.express_kassa.controller;

import com.ahlypalo.express_kassa.constants.HttpHeaders;
import com.ahlypalo.express_kassa.constants.ProduceView;
import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.ReceiptTemplate;
import com.ahlypalo.express_kassa.repository.ReceiptTemplateRepository;
import com.ahlypalo.express_kassa.service.ReceiptTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class ReceiptTemplateController {

    private final ReceiptTemplateRepository receiptTemplateRepository;
    private final ReceiptTemplateService receiptTemplateService;

    public ReceiptTemplateController(ReceiptTemplateRepository receiptTemplateRepository,
                                     ReceiptTemplateService receiptTemplateService) {
        this.receiptTemplateRepository = receiptTemplateRepository;
        this.receiptTemplateService = receiptTemplateService;
    }

    @PostMapping("receipt-template")
    public void saveReceiptTemplate(@RequestBody ReceiptTemplate receiptTemplate, Merchant merchant) {
        receiptTemplateService.addReceiptTemplate(receiptTemplate, merchant);
    }

    @GetMapping("receipt-templates")
    public List<ReceiptTemplate> getReceiptTemplates(Merchant merchant) {
        return receiptTemplateRepository.findAllByMerchantOrMerchantIsNull(merchant);
    }

    @GetMapping("receipt-template")
    public ReceiptTemplate getActiveTemplate(Merchant merchant) {
        return receiptTemplateService.getActive(merchant);
    }

    @PatchMapping("receipt-template/{id}")
    public void setActiveTemplate(@PathVariable Long id, Merchant merchant) {
        receiptTemplateService.setActiveTemplate(id, merchant);
    }
}
