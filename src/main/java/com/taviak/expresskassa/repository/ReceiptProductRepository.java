package com.taviak.expresskassa.repository;

import com.taviak.expresskassa.entity.ReceiptProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptProductRepository extends CrudRepository<ReceiptProduct, Long> {
}
