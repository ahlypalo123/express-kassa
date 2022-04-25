package com.ahlypalo.express_kassa.repository;

import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByMerchant(Merchant merchant);
    List<Product> findAllByMerchantAndBarCode(Merchant merchant, String barCode);
    List<Product> findAllByMerchantAndNameContaining(Merchant merchant, String name);
    @Transactional
    void deleteByIdAndMerchant(Long id, Merchant merchant);
}
