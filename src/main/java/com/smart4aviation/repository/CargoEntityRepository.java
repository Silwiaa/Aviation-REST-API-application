package com.smart4aviation.repository;

import com.smart4aviation.domain.CargoEntity;
import com.smart4aviation.domain.FlightEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoEntityRepository extends CrudRepository<CargoEntity, Long> {
    Optional<CargoEntity> findByFlightEntity(FlightEntity flightEntity);
}