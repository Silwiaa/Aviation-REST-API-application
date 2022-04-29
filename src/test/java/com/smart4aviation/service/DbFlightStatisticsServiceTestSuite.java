package com.smart4aviation.service;

import com.smart4aviation.domain.*;
import com.smart4aviation.exception.CargoEntityNotFoundException;
import com.smart4aviation.exception.FlightEntityNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbFlightStatisticsServiceTestSuite {
    @InjectMocks
    private DbFlightStatisticsService dbFlightStatisticsService;

    @Mock
    private DbFlightEntityService dbFlightEntityService;

    @Mock
    private DbCargoEntityService dbCargoEntityService;

    private static String airportIATACode;
    private static LocalDateTime date;
    private static List<FlightEntity> flightEntityList = new ArrayList<>();
    private static List<Baggage> baggageList = new ArrayList<>();

    @BeforeAll
    public static void beforeAll() {
        airportIATACode = "SEA";
        date = LocalDateTime.of(2022, 12, 24, 0, 0, 0);

        for (int i = 0 ; i < 5 ; i++) {
            flightEntityList.add(FlightEntity.builder().build());
            baggageList.add(Baggage.builder().pieces(i + 1).build());
        }
    }

    @Test
    public void shouldGetFlightStatisticsForGivenAirportIATACodeAndDate() throws CargoEntityNotFoundException, FlightEntityNotFoundException {
        //Given
        List<FlightEntity> departingFlightEntityList = new ArrayList<>();
        departingFlightEntityList.add(flightEntityList.get(0));
        departingFlightEntityList.add(flightEntityList.get(1));

        List<FlightEntity> arrivingFlightEntityList = new ArrayList<>();
        arrivingFlightEntityList.add(flightEntityList.get(2));
        arrivingFlightEntityList.add(flightEntityList.get(3));
        arrivingFlightEntityList.add(flightEntityList.get(4));

        List<Baggage> departingBaggageList = new ArrayList<>();
        departingBaggageList.add(baggageList.get(0));
        departingBaggageList.add(baggageList.get(1));

        List<Baggage> arrivingBaggageList = new ArrayList<>();
        arrivingBaggageList.add(baggageList.get(2));
        arrivingBaggageList.add(baggageList.get(3));
        arrivingBaggageList.add(baggageList.get(4));

        List<CargoEntity> departingCargoEntityList = new ArrayList<>();
        CargoEntity cargoEntity1 = CargoEntity.builder().baggageList(departingBaggageList).build();
        CargoEntity cargoEntity2 = CargoEntity.builder().baggageList(departingBaggageList).build();
        departingCargoEntityList.add(cargoEntity1);
        departingCargoEntityList.add(cargoEntity2);

        List<CargoEntity> arrivingCargoEntityList = new ArrayList<>();
        CargoEntity cargoEntity3 = CargoEntity.builder().baggageList(arrivingBaggageList).build();
        CargoEntity cargoEntity4 = CargoEntity.builder().baggageList(arrivingBaggageList).build();
        CargoEntity cargoEntity5 = CargoEntity.builder().baggageList(arrivingBaggageList).build();
        arrivingCargoEntityList.add(cargoEntity3);
        arrivingCargoEntityList.add(cargoEntity4);
        arrivingCargoEntityList.add(cargoEntity5);

        when(dbFlightEntityService.findDepartingFlights(airportIATACode, date)).thenReturn(departingFlightEntityList);
        when(dbFlightEntityService.findArrivingFlights(airportIATACode, date)).thenReturn(arrivingFlightEntityList);
        when(dbCargoEntityService.findDepartingCargos(airportIATACode, date)).thenReturn(departingCargoEntityList);
        when(dbCargoEntityService.findArrivingCargos(airportIATACode, date)).thenReturn(arrivingCargoEntityList);

        //When
        FlightStatistics resultFlightStatistics = dbFlightStatisticsService.getStatistics(airportIATACode, date);

        //Then
        assertEquals(resultFlightStatistics.getFlightsDeparting(), 2);
        assertEquals(resultFlightStatistics.getFlightsArriving(), 3);
        assertEquals(resultFlightStatistics.getTotalBaggageDeparting(), 6);
        assertEquals(resultFlightStatistics.getTotalBaggageArriving(), 36);
    }
}