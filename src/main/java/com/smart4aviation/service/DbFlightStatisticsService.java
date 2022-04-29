package com.smart4aviation.service;

import com.smart4aviation.domain.Baggage;
import com.smart4aviation.domain.FlightStatistics;
import com.smart4aviation.exception.CargoEntityNotFoundException;
import com.smart4aviation.exception.FlightEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DbFlightStatisticsService {
    private final DbFlightEntityService dbFlightEntityService;
    private final DbCargoEntityService cargoEntityService;

    public FlightStatistics getStatistics(final String airportIATACode, final LocalDateTime date) throws CargoEntityNotFoundException, FlightEntityNotFoundException {
        return FlightStatistics.builder()
                .flightsDeparting(dbFlightEntityService.findDepartingFlights(airportIATACode, date).size())
                .flightsArriving(dbFlightEntityService.findArrivingFlights(airportIATACode, date).size())
                .totalBaggageDeparting(cargoEntityService.findDepartingCargos(airportIATACode, date).stream()
                        .flatMap(baggage -> baggage.getBaggageList().stream())
                        .map(Baggage::getPieces)
                        .reduce(0, (sum, current) -> sum += current))
                .totalBaggageArriving(cargoEntityService.findArrivingCargos(airportIATACode, date).stream()
                        .flatMap(baggage -> baggage.getBaggageList().stream())
                        .map(Baggage::getPieces)
                        .reduce(0, (sum, current) -> sum += current))
                .build();
    }
}