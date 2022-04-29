package com.smart4aviation.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightEntityDto {
    @JsonProperty("flightId")
    private long flightId;

    @JsonProperty("flightNumber")
    private int flightNumber;

    @JsonProperty("departureAirportIATACode")
    private String departureAirportIATACode;

    @JsonProperty("arrivalAirportIATACode")
    private String arrivalAirportIATACode;

    @JsonProperty("departureDate")
    private String departureDate;

    @JsonProperty("arrivalDate")
    private String arrivalDate;
}
