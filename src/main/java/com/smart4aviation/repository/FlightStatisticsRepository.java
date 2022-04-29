package com.smart4aviation.repository;

import com.smart4aviation.domain.FlightStatistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightStatisticsRepository extends CrudRepository<FlightStatistics, Long> {
}