package com.smart4aviation.repository;

import com.smart4aviation.domain.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Long> {
}
