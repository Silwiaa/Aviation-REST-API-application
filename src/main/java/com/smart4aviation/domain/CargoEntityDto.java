package com.smart4aviation.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CargoEntityDto {
    @JsonProperty("cargoId")
    private long cargoId;

    @JsonProperty("flightId")
    private long flightEntityId;

    @JsonProperty("baggageEntity")
    private List<Long> baggageIdList;

    @JsonProperty("cargo")
    private List<Long> cargoIdList;
}