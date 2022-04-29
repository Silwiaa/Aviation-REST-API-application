package com.smart4aviation.repository;

import com.smart4aviation.domain.CargoStatistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoStatisticsRepository extends CrudRepository<CargoStatistics, Long> {
}
