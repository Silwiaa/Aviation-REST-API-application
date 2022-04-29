package com.smart4aviation.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity(name = "BAGGAGE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Baggage {
    @Id
    @NotNull
    @Column(name = "ID", unique = true)
    private long id;

    @NotNull
    @Column(name = "WEIGHT")
    private double totalWeight;

    @NotNull
    @Column(name = "WEIGHT_UNIT")
    private WeightUnit weightUnit;

    @NotNull
    @Column(name = "PIECES")
    private int pieces;

    @ManyToOne
    @JoinColumn(name = "CARGO_ENTITY_ID")
    private CargoEntity cargoEntity;
}
