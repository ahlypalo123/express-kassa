package com.ahlypalo.express_kassa.repository;

import com.ahlypalo.express_kassa.entity.ReceiptTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptTemplateRepository extends CrudRepository<ReceiptTemplate, Long> {
    public List<ReceiptTemplate> findWhereMerchantIdAndMerchantIdIsNull(Long merchantId);
}
