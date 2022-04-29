package com.smart4aviation.repository;

import com.smart4aviation.domain.FlightEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightEntityRepository extends CrudRepository<FlightEntity, Long> {
    List<FlightEntity> findAllByDepartureAirportIATACode(String departureAirportIATACod);
    List<FlightEntity> findAllByArrivalAirportIATACode(String arrivalAirportIATACode);
    Optional<FlightEntity> findByFlightNumber(int flightNumber);
}