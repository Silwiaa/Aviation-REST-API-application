package com.smart4aviation.service;

import com.smart4aviation.domain.CargoEntity;
import com.smart4aviation.domain.FlightEntity;
import com.smart4aviation.exception.FlightEntityNotFoundException;
import com.smart4aviation.repository.FlightEntityRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbFlightEntityServiceTestSuite {
    @InjectMocks
    private DbFlightEntityService dbFlightEntityService;

    @Mock
    private FlightEntityRepository flightEntityRepository;

    private static String airportIATACode1;
    private static String airportIATACode2;
    private static LocalDateTime date;
    private static int flightNumber1;
    private static int flightNumber2;
    private static LocalDateTime departureDate1;
    private static LocalDateTime departureDate2;
    private static LocalDateTime arrivalDate1;
    private static LocalDateTime arrivalDate2;
    private static FlightEntity flightEntity1;
    private static FlightEntity flightEntity2;
    private static Optional<CargoEntity> cargoEntity1;
    private static Optional<CargoEntity> cargoEntity2;

    @BeforeAll
    public static void beforeAll() {
        airportIATACode1 = "SEA";
        airportIATACode2 = "PPX";
        flightNumber1 = 8300;
        flightNumber2 = 7334;
        date = LocalDateTime.of(2022, 12, 24, 0, 0, 0);
        departureDate1 = LocalDateTime.of(2022, 12, 24, 7, 53, 31);
        departureDate2 = LocalDateTime.of(2022, 11, 24, 7, 53, 31);
        arrivalDate1 = departureDate1;
        arrivalDate2 = departureDate2;
        flightEntity1 = FlightEntity.builder()
                .flightId(1L)
                .flightNumber(flightNumber1)
                .departureDate(departureDate1)
                .arrivalDate(arrivalDate1)
                .departureAirportIATACode(airportIATACode1)
                .arrivalAirportIATACode(airportIATACode2)
                .build();
        flightEntity2 = FlightEntity.builder()
                .flightId(2L)
                .flightNumber(flightNumber2)
                .departureDate(departureDate2)
                .arrivalDate(arrivalDate2)
                .departureAirportIATACode(airportIATACode1)
                .arrivalAirportIATACode(airportIATACode2)
                .build();
        cargoEntity1 = Optional.ofNullable(CargoEntity.builder().flightEntity(flightEntity1).build());
        cargoEntity2 = Optional.ofNullable(CargoEntity.builder().flightEntity(flightEntity2).build());
    }

    @Test
    public void shouldFindDepartingFlights() {
        //Given
        List<FlightEntity> departingFlightEntityList = new ArrayList<>();
        departingFlightEntityList.add(flightEntity1);
        departingFlightEntityList.add(flightEntity2);

        when(flightEntityRepository.findAllByDepartureAirportIATACode(airportIATACode1)).thenReturn(departingFlightEntityList);

        //When
        List<FlightEntity> resultFlightEntityList = dbFlightEntityService.findDepartingFlights(airportIATACode1, date);

        //Then
        assertEquals(resultFlightEntityList.size(), 1);
        assertEquals(resultFlightEntityList.get(0).getDepartureAirportIATACode(), airportIATACode1);
        assertEquals(resultFlightEntityList.get(0).getDepartureDate().getYear(), date.getYear());
        assertEquals(resultFlightEntityList.get(0).getDepartureDate().getMonth(), date.getMonth());
        assertEquals(resultFlightEntityList.get(0).getDepartureDate().getDayOfMonth(), date.getDayOfMonth());
    }

    @Test
    public void shouldFindArrivingFlights() {
        //Given
        List<FlightEntity> arrivingFlightEntityList = new ArrayList<>();
        arrivingFlightEntityList.add(flightEntity1);
        arrivingFlightEntityList.add(flightEntity2);

        when(flightEntityRepository.findAllByArrivalAirportIATACode(airportIATACode2)).thenReturn(arrivingFlightEntityList);

        //When
        List<FlightEntity> resultFlightEntityList = dbFlightEntityService.findArrivingFlights(airportIATACode2, date);

        //Then
        assertEquals(resultFlightEntityList.size(), 1);
        assertEquals(resultFlightEntityList.get(0).getArrivalAirportIATACode(), airportIATACode2);
        assertEquals(resultFlightEntityList.get(0).getArrivalDate().getYear(), date.getYear());
        assertEquals(resultFlightEntityList.get(0).getArrivalDate().getMonth(), date.getMonth());
        assertEquals(resultFlightEntityList.get(0).getArrivalDate().getDayOfMonth(), date.getDayOfMonth());
    }

    @Test
    public void shouldFindFlight() throws FlightEntityNotFoundException {
        //Given
        when(flightEntityRepository.findById(1L)).thenReturn(Optional.ofNullable(flightEntity1));

        //When
        FlightEntity resultFlightEntity = dbFlightEntityService.findFlight(1L);

        //Then
        assertEquals(resultFlightEntity.getFlightId(), 1L);
    }

    @Test
    public void shouldFindFlightByFlightNumber() throws FlightEntityNotFoundException {
        //Given
        when(flightEntityRepository.findByFlightNumber(flightNumber1)).thenReturn(Optional.ofNullable(flightEntity1));

        //When
        FlightEntity resultFlightEntity = dbFlightEntityService.findFlightByFlightNumber(flightNumber1);

        //Then
        assertEquals(resultFlightEntity.getFlightNumber(), flightNumber1);
    }
}
