package com.taviak.expresskassa.repository;

import com.taviak.expresskassa.entity.Merchant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends CrudRepository<Merchant, Long> {
  Optional<Merchant> findOneByEmail(String email);
}
