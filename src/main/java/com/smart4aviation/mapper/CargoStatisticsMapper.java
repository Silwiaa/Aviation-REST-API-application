package com.smart4aviation.mapper;

import com.smart4aviation.domain.CargoStatistics;
import com.smart4aviation.domain.CargoStatisticsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class CargoStatisticsMapper {
    public CargoStatisticsDto mapToCargoStatisticsDto(final CargoStatistics cargoStatistics) {
        return CargoStatisticsDto.builder()
                .id(cargoStatistics.getId())
                .cargoWeightLb(cargoStatistics.getCargoWeightLb())
                .cargoWeightKg(cargoStatistics.getCargoWeightKg())
                .baggageWeightLb(cargoStatistics.getBaggageWeightLb())
                .baggageWeightKg(cargoStatistics.getBaggageWeightKg())
                .totalWeightLb(cargoStatistics.getTotalWeightLb())
                .totalWeightKg(cargoStatistics.getTotalWeightKg())
                .build();
    }
}
