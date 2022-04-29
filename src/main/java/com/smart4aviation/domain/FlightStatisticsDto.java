package com.smart4aviation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class FlightStatisticsDto {
    private long id;
    private int flightsDeparting;
    private int flightsArriving;
    private int totalBaggageDeparting;
    private int totalBaggageArriving;
}
