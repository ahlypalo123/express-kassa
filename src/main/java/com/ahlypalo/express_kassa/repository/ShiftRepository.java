package com.ahlypalo.express_kassa.repository;

import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.Shift;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Optional;

public interface ShiftRepository extends CrudRepository<Shift, Long> {

}
