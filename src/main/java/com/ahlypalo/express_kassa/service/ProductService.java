package com.ahlypalo.express_kassa.service;

import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.Product;
import com.ahlypalo.express_kassa.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct(Product product, Merchant merchant) {
        product.setMerchant(merchant);
        productRepository.save(product);
    }

    public List<Product> findAllByMerchant(Merchant merchant, String name, String barCode) {
        if (StringUtils.hasText(barCode)) {
            return productRepository.findAllByMerchantAndBarCode(merchant, barCode);
        }
        if (StringUtils.hasText(name)) {
            return productRepository.findAllByMerchantAndNameContaining(merchant, name);
        }
        return productRepository.findAllByMerchant(merchant);
    }
}
