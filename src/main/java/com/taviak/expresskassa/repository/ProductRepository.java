package com.taviak.expresskassa.repository;

import com.taviak.expresskassa.entity.Merchant;
import com.taviak.expresskassa.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByMerchant(Merchant merchant);
    List<Product> findAllByMerchantAndBarCode(Merchant merchant, String barCode);
    List<Product> findAllByMerchantAndNameContaining(Merchant merchant, String name);
    @Transactional
    void deleteByIdAndMerchant(Long id, Merchant merchant);
}
