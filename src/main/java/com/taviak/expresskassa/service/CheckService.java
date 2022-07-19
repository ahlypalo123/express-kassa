package com.taviak.expresskassa.service;

import com.taviak.expresskassa.constants.OrderColumn;
import com.taviak.expresskassa.entity.*;
import com.taviak.expresskassa.repository.CheckRepository;
import com.taviak.expresskassa.repository.ReceiptProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckService {

    private final CheckRepository checkRepository;
    private final ReceiptProductRepository receiptProductRepository;

    public CheckService(CheckRepository checkRepository, ReceiptProductRepository receiptProductRepository) {
        this.checkRepository = checkRepository;
        this.receiptProductRepository = receiptProductRepository;
    }

    public Check createCheck(Check check, Merchant merchant) {
        Check c = new Check();
        MerchantDetails details = merchant.getDetails();
        if (details != null) {
            c.setInn(details.getInn());
            c.setName(details.getName());
            if (details.getShift() != null) {
                c.setEmployeeName(details.getShift().getEmployeeName());
            }
            c.setAddress(details.getAddress());
            c.setTaxType(details.getTaxType());
        }
        c.setMerchant(merchant);

        c.setTotal(check.getTotal());
        c.setCash(check.getCash());
        c.setPaymentMethod(check.getPaymentMethod());

        c = checkRepository.save(c);

        saveProducts(c, check.getProducts());

        return c;
    }

    private void saveProducts(Check check, List<ReceiptProduct> products) {
        products.forEach(p -> p.setCheck(check));
        products = (List<ReceiptProduct>) receiptProductRepository.saveAll(products);
        check.setProducts(products);
    }

    public Check updateCheck(Check check, Merchant merchant) {
        check.setMerchant(merchant);
        check.setCompleted(true);
        check = checkRepository.save(check);

        return check;
    }

    public List<Check> getCheckHistory(Merchant merchant, OrderColumn orderColumn) {
        return checkRepository.getAllByMerchantSorting(merchant, orderColumn.getName());
    }
}
