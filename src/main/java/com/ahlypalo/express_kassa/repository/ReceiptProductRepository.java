package com.ahlypalo.express_kassa.repository;

import com.ahlypalo.express_kassa.entity.ReceiptProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptProductRepository extends CrudRepository<ReceiptProduct, Long> {
}
