package com.ahlypalo.express_kassa.service;

import com.ahlypalo.express_kassa.constants.OrderColumn;
import com.ahlypalo.express_kassa.entity.*;
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
    private final ReceiptProductRepository receiptProductRepository;

    public CheckService(CheckRepository checkRepository, ReceiptProductRepository receiptProductRepository) {
        this.checkRepository = checkRepository;
        this.receiptProductRepository = receiptProductRepository;
    }

    public Check saveCheck(Check check, Merchant merchant) {
        List<ReceiptProduct> products = (List<ReceiptProduct>) receiptProductRepository.saveAll(check.getProducts());
        MerchantDetails details = merchant.getDetails();
        Shift shift = merchant.getShift();

        check.setProducts(products);
        check.setMerchant(merchant);
        check.setDate(new Date());
        if (details != null) {
            check.setInn(details.getInn());
            check.setAddress(details.getAddress());
            check.setName(details.getName());
            check.setTaxPercent(details.getTaxPercent());
            check.setTaxType(details.getTaxType());
        }
        if (shift != null) {
            check.setEmployeeName(shift.getEmployeeName());
        }

        Long id = checkRepository.save(check).getId();
        check.setId(id);

        return check;
    }

    public List<Check> getCheckHistory(Merchant merchant, OrderColumn orderColumn) {
        return checkRepository.getAllByMerchant(merchant, Sort.by(orderColumn.getName()));
    }
}
