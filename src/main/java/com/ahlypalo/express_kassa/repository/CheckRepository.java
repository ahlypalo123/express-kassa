package com.ahlypalo.express_kassa.repository;

import com.ahlypalo.express_kassa.entity.Check;
import com.ahlypalo.express_kassa.entity.Merchant;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CheckRepository extends CrudRepository<Check, Long> {
    List<Check> getAllByMerchant(Merchant merchant, Sort sort);
}
