package com.ahlypalo.express_kassa.service;

import com.ahlypalo.express_kassa.constants.OrderColumn;
import com.ahlypalo.express_kassa.entity.*;
import com.ahlypalo.express_kassa.exception.ApiException;
import com.ahlypalo.express_kassa.repository.CheckRepository;
import com.ahlypalo.express_kassa.repository.ProductRepository;
import com.ahlypalo.express_kassa.repository.ReceiptProductRepository;
import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

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
        c.setInn(details.getInn());
        c.setName(details.getName());
        c.setEmployeeName(details.getShift().getEmployeeName());
        c.setAddress(details.getAddress());
        c.setMerchant(merchant);
        c.setTaxType(details.getTaxType());
        // c.setMerchantData(details.getData());

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

//        products.forEach(p -> p.setCheck(check));
//        products = (List<ReceiptProduct>) receiptProductRepository.saveAll(products);
//        check.setProducts(products);

        return check;
    }

    public List<Check> getCheckHistory(Merchant merchant, OrderColumn orderColumn) {
        Object[] objects = Arrays.stream(OrderColumn.values())
                .sorted(Comparator.comparingInt(e -> e == orderColumn ? 0 : 1))
                .map(OrderColumn::getName)
                .toArray();
        String[] columns = Arrays.copyOf(objects, objects.length, String[].class);
        Sort sort = Sort.by(Sort.Direction.DESC, columns);
        return checkRepository.getAllByMerchant(merchant, sort);
    }
}
