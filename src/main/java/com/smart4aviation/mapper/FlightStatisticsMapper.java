package com.smart4aviation.mapper;

import com.smart4aviation.domain.FlightStatistics;
import com.smart4aviation.domain.FlightStatisticsDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class FlightStatisticsMapper {
    public FlightStatisticsDto mapToFlightStatisticsDto(final FlightStatistics flightEntityStatistics) {
        return FlightStatisticsDto.builder()
                .id(flightEntityStatistics.getId())
                .flightsDeparting(flightEntityStatistics.getFlightsDeparting())
                .flightsArriving(flightEntityStatistics.getFlightsArriving())
                .totalBaggageDeparting(flightEntityStatistics.getTotalBaggageDeparting())
                .totalBaggageArriving(flightEntityStatistics.getTotalBaggageArriving())
                .build();
    }
}