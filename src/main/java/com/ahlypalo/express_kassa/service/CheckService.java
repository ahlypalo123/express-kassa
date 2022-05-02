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

    public Check saveCheck(Check check, Merchant merchant) {
        check.setMerchant(merchant);
        Check c = checkRepository.save(check);
        check.getProducts().forEach(p -> p.setCheck(c));
        List<ReceiptProduct> products = (List<ReceiptProduct>) receiptProductRepository.saveAll(check.getProducts());
        c.setProducts(products);
        return c;
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
