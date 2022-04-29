package com.smart4aviation.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CargoDto {
    @JsonProperty("id")
    private long id;

    @JsonProperty("totalWeight")
    private double weight;

    @JsonProperty("weightUnit")
    private WeightUnit weightUnit;

    @JsonProperty("pieces")
    private int pieces;

    private long cargoEntityId;
}
