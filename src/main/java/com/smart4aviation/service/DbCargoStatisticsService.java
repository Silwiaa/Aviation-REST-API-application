package com.smart4aviation.service;

import com.smart4aviation.domain.CargoEntity;
import com.smart4aviation.domain.CargoStatistics;
import com.smart4aviation.domain.WeightUnit;
import com.smart4aviation.exception.CargoEntityNotFoundException;
import com.smart4aviation.exception.FlightEntityNotFoundException;
import com.smart4aviation.mapper.WeightUnitMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DbCargoStatisticsService {
    private final DbCargoEntityService dbCargoEntityService;
    private final WeightUnitMapper weightUnitMapper;

    public CargoStatistics getStatistics(final int flightNumber) throws CargoEntityNotFoundException, FlightEntityNotFoundException {
        CargoEntity cargoEntity = dbCargoEntityService.findCargoEntity(flightNumber);
        BigDecimal cargoWeightLb = cargoEntity.getCargoList().stream()
                .map(cargo -> new BigDecimal(cargo.getPieces() * getTotalWeightInLb(cargo.getTotalWeight(), cargo.getWeightUnit())))
                .reduce(BigDecimal.ZERO, (sum, current) -> sum = sum.add(current));
        BigDecimal cargoWeightKg = cargoEntity.getCargoList().stream()
                .map(cargo -> new BigDecimal(cargo.getPieces() * getTotalWeightInKg(cargo.getTotalWeight(), cargo.getWeightUnit())))
                .reduce(BigDecimal.ZERO, (sum, current) -> sum = sum.add(current));
        BigDecimal baggageWeightLb = cargoEntity.getBaggageList().stream()
                .map(baggage -> new BigDecimal(baggage.getPieces() * getTotalWeightInLb(baggage.getTotalWeight(), baggage.getWeightUnit())))
                .reduce(BigDecimal.ZERO, (sum, current) -> sum = sum.add(current));
        BigDecimal baggageWeightKg = cargoEntity.getBaggageList().stream()
                .map(baggage -> new BigDecimal(baggage.getPieces() * getTotalWeightInKg(baggage.getTotalWeight(), baggage.getWeightUnit())))
                .reduce(BigDecimal.ZERO, (sum, current) -> sum = sum.add(current));

        return CargoStatistics.builder()
                .cargoWeightLb(cargoWeightLb)
                .cargoWeightKg(cargoWeightKg)
                .baggageWeightLb(baggageWeightLb)
                .baggageWeightKg(baggageWeightKg)
                .totalWeightLb(cargoWeightLb.add(baggageWeightLb))
                .totalWeightKg(cargoWeightKg.add(baggageWeightKg))
                .build();
    }

    private double getTotalWeightInLb(double totalWeight, WeightUnit weightUnit) {
        if (WeightUnit.lb == weightUnit) {
            return totalWeight;
        } else {
            return weightUnitMapper.mapToLb(totalWeight);
        }
    }

    private double getTotalWeightInKg(double totalWeight, WeightUnit weightUnit) {
        if (WeightUnit.kg == weightUnit) {
            return totalWeight;
        } else {
            return weightUnitMapper.mapToKg(totalWeight);
        }
    }
}
