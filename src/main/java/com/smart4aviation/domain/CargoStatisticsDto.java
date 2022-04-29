package com.smart4aviation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
public class CargoStatisticsDto {
    private long id;
    private BigDecimal cargoWeightLb;
    private BigDecimal cargoWeightKg;
    private BigDecimal baggageWeightLb;
    private BigDecimal baggageWeightKg;
    private BigDecimal totalWeightLb;
    private BigDecimal totalWeightKg;
}
