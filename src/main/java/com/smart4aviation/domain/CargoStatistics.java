package com.smart4aviation.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "CARGO_STATISTICS")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargoStatistics {
    @Id
    @NotNull
    @Column(name = "ID", unique = true)
    private long id;

    @NotNull
    @Column(name = "CARGO_WEIGHT_LB")
    private BigDecimal cargoWeightLb;

    @NotNull
    @Column(name = "CARGO_WEIGHT_KG")
    private BigDecimal cargoWeightKg;

    @NotNull
    @Column(name = "BAGGAGE_WEIGHT_LB")
    private BigDecimal baggageWeightLb;

    @NotNull
    @Column(name = "BAGGAGE_WEIGHT_KG")
    private BigDecimal baggageWeightKg;

    @NotNull
    @Column(name = "TOTAL_WEIGHT_LB")
    private BigDecimal totalWeightLb;

    @NotNull
    @Column(name = "TOTAL_WEIGHT_KG")
    private BigDecimal totalWeightKg;
}
