package com.smart4aviation.service;

import com.smart4aviation.domain.CargoEntity;
import com.smart4aviation.domain.FlightEntity;
import com.smart4aviation.exception.CargoEntityNotFoundException;
import com.smart4aviation.exception.FlightEntityNotFoundException;
import com.smart4aviation.repository.CargoEntityRepository;
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
public class DbCargoEntityServiceTestSuite {
    @InjectMocks
    private DbCargoEntityService dbCargoEntityService;

    @Mock
    private CargoEntityRepository cargoEntityRepository;

    @Mock
    private DbFlightEntityService dbFlightEntityService;

    private static String airportIATACode1;
    private static String airportIATACode2;
    private static LocalDateTime date;
    private static LocalDateTime departureDate;
    private static LocalDateTime arrivalDate;
    private static int flightNumber1;
    private static int flightNumber2;
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
        departureDate = LocalDateTime.of(2022, 12, 24, 7, 53, 31);
        arrivalDate = departureDate;
        flightEntity1 = FlightEntity.builder()
                .flightId(1L)
                .flightNumber(flightNumber1)
                .departureDate(departureDate)
                .arrivalDate(arrivalDate)
                .departureAirportIATACode(airportIATACode1)
                .arrivalAirportIATACode(airportIATACode2)
                .build();
        flightEntity2 = FlightEntity.builder()
                .flightId(2L)
                .flightNumber(flightNumber2)
                .departureDate(departureDate)
                .arrivalDate(arrivalDate)
                .departureAirportIATACode(airportIATACode1)
                .arrivalAirportIATACode(airportIATACode2)
                .build();
        cargoEntity1 = Optional.ofNullable(CargoEntity.builder().cargoEntityId(1L).flightEntity(flightEntity1).build());
        cargoEntity2 = Optional.ofNullable(CargoEntity.builder().cargoEntityId(2L).flightEntity(flightEntity2).build());
    }

    @Test
    public void shouldFindDepartingCargos() throws FlightEntityNotFoundException, CargoEntityNotFoundException {
        //Given
        List<FlightEntity> departingFlightEntityList = new ArrayList<>();
        departingFlightEntityList.add(flightEntity1);
        departingFlightEntityList.add(flightEntity2);

        when(dbFlightEntityService.findDepartingFlights(airportIATACode1, date)).thenReturn(departingFlightEntityList);
        when(dbFlightEntityService.findFlight(1L)).thenReturn(flightEntity1);
        when(dbFlightEntityService.findFlight(2L)).thenReturn(flightEntity2);
        when(cargoEntityRepository.findByFlightEntity(flightEntity1)).thenReturn(cargoEntity1);
        when(cargoEntityRepository.findByFlightEntity(flightEntity2)).thenReturn(cargoEntity2);

        //When
        List<CargoEntity> resultCargoEntityList = dbCargoEntityService.findDepartingCargos(airportIATACode1, date);

        //Then
        assertEquals(resultCargoEntityList.size(), 2);
        assertEquals(resultCargoEntityList.get(0).getFlightEntity().getDepartureAirportIATACode(), "SEA");
        assertEquals(resultCargoEntityList.get(1).getFlightEntity().getDepartureAirportIATACode(), "SEA");
        assertEquals(resultCargoEntityList.get(0).getFlightEntity().getDepartureDate().getYear(), date.getYear());
        assertEquals(resultCargoEntityList.get(0).getFlightEntity().getDepartureDate().getMonthValue(), date.getMonthValue());
        assertEquals(resultCargoEntityList.get(0).getFlightEntity().getDepartureDate().getDayOfMonth(), date.getDayOfMonth());
        assertEquals(resultCargoEntityList.get(1).getFlightEntity().getDepartureDate().getYear(), date.getYear());
        assertEquals(resultCargoEntityList.get(1).getFlightEntity().getDepartureDate().getMonthValue(), date.getMonthValue());
        assertEquals(resultCargoEntityList.get(1).getFlightEntity().getDepartureDate().getDayOfMonth(), date.getDayOfMonth());
    }

    @Test
    public void shouldFindArrivingCargoEntities() throws FlightEntityNotFoundException, CargoEntityNotFoundException {
        //Given
        List<FlightEntity> arrivingFlightEntityList = new ArrayList<>();
        arrivingFlightEntityList.add(flightEntity1);
        arrivingFlightEntityList.add(flightEntity2);

        when(dbFlightEntityService.findArrivingFlights(airportIATACode2, date)).thenReturn(arrivingFlightEntityList);
        when(dbFlightEntityService.findFlight(1L)).thenReturn(flightEntity1);
        when(dbFlightEntityService.findFlight(2L)).thenReturn(flightEntity2);
        when(cargoEntityRepository.findByFlightEntity(flightEntity1)).thenReturn(cargoEntity1);
        when(cargoEntityRepository.findByFlightEntity(flightEntity2)).thenReturn(cargoEntity2);

        //When
        List<CargoEntity> resultCargoEntityList = dbCargoEntityService.findArrivingCargos(airportIATACode2, date);

        //Then
        assertEquals(resultCargoEntityList.size(), 2);
        assertEquals(resultCargoEntityList.get(0).getFlightEntity().getArrivalAirportIATACode(), "PPX");
        assertEquals(resultCargoEntityList.get(1).getFlightEntity().getArrivalAirportIATACode(), "PPX");
        assertEquals(resultCargoEntityList.get(0).getFlightEntity().getArrivalDate().getYear(), date.getYear());
        assertEquals(resultCargoEntityList.get(0).getFlightEntity().getArrivalDate().getMonthValue(), date.getMonthValue());
        assertEquals(resultCargoEntityList.get(0).getFlightEntity().getArrivalDate().getDayOfMonth(), date.getDayOfMonth());
        assertEquals(resultCargoEntityList.get(1).getFlightEntity().getArrivalDate().getYear(), date.getYear());
        assertEquals(resultCargoEntityList.get(1).getFlightEntity().getArrivalDate().getMonthValue(), date.getMonthValue());
        assertEquals(resultCargoEntityList.get(1).getFlightEntity().getArrivalDate().getDayOfMonth(), date.getDayOfMonth());
    }

    @Test
    public void shouldFindCargoEntityByFlightNumber() throws FlightEntityNotFoundException, CargoEntityNotFoundException {
        //Given
        when(dbFlightEntityService.findFlightByFlightNumber(flightNumber1)).thenReturn(flightEntity1);
        when(cargoEntityRepository.findByFlightEntity(flightEntity1)).thenReturn(cargoEntity1);

        //When
        CargoEntity resultCargoEntity = dbCargoEntityService.findCargoEntity(flightNumber1);

        //Then
        assertEquals(resultCargoEntity.getFlightEntity().getFlightNumber(), 8300);
    }

    @Test
    public void shouldFindCargoEntityByFlightEntity() throws CargoEntityNotFoundException {
        //Given
        when(cargoEntityRepository.findByFlightEntity(flightEntity1)).thenReturn(cargoEntity1);

        //When
        CargoEntity resultCargoEntity = dbCargoEntityService.findCargoEntityByFlightEntity(flightEntity1);

        //Then
        assertEquals(resultCargoEntity.getFlightEntity(), flightEntity1);
    }

    @Test
    public void shouldFindCargoEntityByCargoId() throws CargoEntityNotFoundException {
        //Given
        when(cargoEntityRepository.findById(1L)).thenReturn(cargoEntity1);

        //When
        CargoEntity resultCargoEntity = dbCargoEntityService.findCargoEntityById(1L);

        //Then
        assertEquals(resultCargoEntity.getCargoEntityId(), 1L);
    }
}
