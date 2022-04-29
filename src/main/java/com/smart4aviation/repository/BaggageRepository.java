package com.smart4aviation.repository;

import com.smart4aviation.domain.Baggage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaggageRepository extends CrudRepository<Baggage, Long> {
}
