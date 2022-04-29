package com.smart4aviation.service;

import com.smart4aviation.domain.FlightEntity;
import com.smart4aviation.exception.FlightEntityNotFoundException;
import com.smart4aviation.repository.FlightEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DbFlightEntityService {
    private final FlightEntityRepository flightEntityRepository;

    public List<FlightEntity> findDepartingFlights(final String departureAirportIATACode, final LocalDateTime departureDate) {
        List<FlightEntity> flightEntityList = flightEntityRepository.findAllByDepartureAirportIATACode(departureAirportIATACode);
        return new ArrayList<>(flightEntityList.stream()
                .filter(y -> y.getDepartureDate().getYear() == departureDate.getYear())
                .filter(m -> m.getDepartureDate().getMonth() == departureDate.getMonth())
                .filter(d -> d.getDepartureDate().getDayOfMonth() == departureDate.getDayOfMonth())
                .collect(Collectors.toList()));
    }

    public List<FlightEntity> findArrivingFlights(final String arrivalAirportIATACode, final LocalDateTime arrivalDate) {
        List<FlightEntity> flightEntityList = flightEntityRepository.findAllByArrivalAirportIATACode(arrivalAirportIATACode);
        return new ArrayList<>(flightEntityList.stream()
                .filter(y -> y.getArrivalDate().getYear() == arrivalDate.getYear())
                .filter(m -> m.getArrivalDate().getMonth() == arrivalDate.getMonth())
                .filter(d -> d.getArrivalDate().getDayOfMonth() == arrivalDate.getDayOfMonth())
                .collect(Collectors.toList()));
    }

    public FlightEntity findFlight(long flightId) throws FlightEntityNotFoundException {
        return flightEntityRepository.findById(flightId).orElseThrow(FlightEntityNotFoundException::new);
    }

    public FlightEntity findFlightByFlightNumber(int flightNumber) throws FlightEntityNotFoundException {
        return flightEntityRepository.findByFlightNumber(flightNumber).orElseThrow(FlightEntityNotFoundException::new);
    }
}