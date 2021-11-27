package com.ahlypalo.express_kassa.repository;

import com.ahlypalo.express_kassa.entity.Merchant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {
  Optional<Merchant> findOneByEmail(String email);
}
