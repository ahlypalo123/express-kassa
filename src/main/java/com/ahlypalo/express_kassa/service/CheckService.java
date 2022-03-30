package com.ahlypalo.express_kassa.service;

import com.ahlypalo.express_kassa.constants.OrderColumn;
import com.ahlypalo.express_kassa.entity.Check;
import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.ReceiptProduct;
import com.ahlypalo.express_kassa.entity.Shift;
import com.ahlypalo.express_kassa.exception.ApiException;
import com.ahlypalo.express_kassa.repository.CheckRepository;
import com.ahlypalo.express_kassa.repository.ProductRepository;
import com.ahlypalo.express_kassa.repository.ReceiptProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CheckService {

    private final CheckRepository checkRepository;
    private final ShiftService shiftService;
    private final ReceiptProductRepository receiptProductRepository;

    public CheckService(CheckRepository checkRepository, ShiftService shiftService, ReceiptProductRepository receiptProductRepository) {
        this.checkRepository = checkRepository;
        this.shiftService = shiftService;
        this.receiptProductRepository = receiptProductRepository;
    }

    public void saveCheck(Check check, Merchant merchant) {
        check.setProducts((List<ReceiptProduct>) receiptProductRepository.saveAll(check.getProducts()));

        check.setMerchantDetails(merchant.getDetails());
        check.setMerchant(merchant);
        check.setDate(new Date());

        Optional<Shift> opt = shiftService.getCurrentShift(merchant);
        opt.ifPresent(check::setShift);

        checkRepository.save(check);
    }

    public List<Check> getCheckHistory(Merchant merchant, OrderColumn orderColumn) {
        return checkRepository.getAllByMerchant(merchant, Sort.by(orderColumn.getName()));
    }
}
