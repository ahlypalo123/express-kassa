package com.ahlypalo.express_kassa.repository;

import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.ReceiptTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiptTemplateRepository extends CrudRepository<ReceiptTemplate, Long> {
    List<ReceiptTemplate> findAllByMerchantOrMerchantIsNull(Merchant merchant);
    Optional<ReceiptTemplate> findByIsActiveIsTrueAndMerchant(Merchant merchant);
    Optional<ReceiptTemplate> findByIdAndMerchant(Long id, Merchant merchant);
}
