package com.taviak.expresskassa.repository;

import com.taviak.expresskassa.entity.Check;
import com.taviak.expresskassa.entity.Merchant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckRepository extends CrudRepository<Check, Long> {

    @Query("SELECT c FROM Check c JOIN FETCH c.products p WHERE c.merchant = :merchant ORDER BY :orderColumn")
    List<Check> getAllByMerchantSorting(Merchant merchant, String orderColumn);
}
