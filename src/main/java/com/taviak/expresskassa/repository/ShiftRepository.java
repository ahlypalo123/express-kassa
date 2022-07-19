package com.taviak.expresskassa.repository;

import com.taviak.expresskassa.entity.Shift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends CrudRepository<Shift, Long> {

}
