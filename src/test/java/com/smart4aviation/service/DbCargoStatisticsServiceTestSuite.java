package com.smart4aviation.service;

import com.smart4aviation.domain.*;
import com.smart4aviation.exception.CargoEntityNotFoundException;
import com.smart4aviation.exception.FlightEntityNotFoundException;
import com.smart4aviation.mapper.WeightUnitMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbCargoStatisticsServiceTestSuite {
    @InjectMocks
    private DbCargoStatisticsService dbCargoStatisticsService;

    @Mock
    private DbCargoEntityService dbCargoEntityService;

    @Mock
    private WeightUnitMapper weightUnitMapper;

    private static List<Baggage> baggageList = new ArrayList<>();
    private static List<Cargo> cargoList = new ArrayList<>();
    private static int flightNumber;
    private static FlightEntity flightEntity;

    @BeforeAll
    public static void beforeAll() {
        for (int i = 1 ; i < 3 ; i++) {
            baggageList.add(Baggage.builder()
                    .totalWeight(i * 19)
                    .weightUnit(WeightUnit.kg)
                    .pieces(i)
                    .build());

            cargoList.add(Cargo.builder()
                    .totalWeight(i * 19)
                    .weightUnit(WeightUnit.kg)
                    .pieces(i)
                    .build());
        }

        flightNumber = 8300;
        flightEntity = FlightEntity.builder().flightNumber(flightNumber).build();
    }

    @Test
    public void shouldGetCargoStatisticsForGivenFlightNumber() throws CargoEntityNotFoundException, FlightEntityNotFoundException {
        //Given
        CargoEntity cargoEntity = CargoEntity.builder()
                .flightEntity(flightEntity)
                .cargoList(cargoList)
                .baggageList(baggageList)
                .build();

        when(dbCargoEntityService.findCargoEntity(flightNumber)).thenReturn(cargoEntity);
        when(weightUnitMapper.mapToLb(cargoList.get(0).getTotalWeight())).thenReturn(27.00);
        when(weightUnitMapper.mapToLb(cargoList.get(1).getTotalWeight())).thenReturn(84.00);
        when(weightUnitMapper.mapToLb(baggageList.get(0).getTotalWeight())).thenReturn(27.00);
        when(weightUnitMapper.mapToLb(baggageList.get(1).getTotalWeight())).thenReturn(84.00);

        //When
        CargoStatistics resultCargoStatistics = dbCargoStatisticsService.getStatistics(flightNumber);

        //Then
        assertEquals(resultCargoStatistics.getCargoWeightLb(), new BigDecimal(195));
        assertEquals(resultCargoStatistics.getCargoWeightKg(), new BigDecimal(95));
        assertEquals(resultCargoStatistics.getBaggageWeightLb(), new BigDecimal(195));
        assertEquals(resultCargoStatistics.getBaggageWeightKg(), new BigDecimal(95));
        assertEquals(resultCargoStatistics.getTotalWeightLb(), new BigDecimal(390));
        assertEquals(resultCargoStatistics.getTotalWeightKg(), new BigDecimal(190));
    }
}
