package com.smart4aviation.controller;

import com.smart4aviation.domain.CargoStatistics;
import com.smart4aviation.domain.CargoStatisticsDto;
import com.smart4aviation.exception.CargoEntityNotFoundException;
import com.smart4aviation.exception.FlightEntityNotFoundException;
import com.smart4aviation.exception.InvalidFlightNumberException;
import com.smart4aviation.mapper.CargoStatisticsMapper;
import com.smart4aviation.service.DbCargoStatisticsService;
import com.smart4aviation.validator.FlightNumberValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(CargoStatisticsController.class)
public class CargoStatisticsControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbCargoStatisticsService cargoStatisticsService;

    @MockBean
    private CargoStatisticsMapper cargoStatisticsMapper;

    @MockBean
    private FlightNumberValidator flightNumberValidator;

    @Test
    void shouldFetchCargoStatistics() throws InvalidFlightNumberException, CargoEntityNotFoundException, FlightEntityNotFoundException, Exception {
        //Given
        CargoStatistics cargoStatistics = CargoStatistics.builder()
                .id(1L)
                .cargoWeightLb(new BigDecimal(400000))
                .cargoWeightKg(new BigDecimal(181437))
                .baggageWeightLb(new BigDecimal(300))
                .baggageWeightKg(new BigDecimal(136))
                .totalWeightLb(new BigDecimal(400300))
                .totalWeightKg(new BigDecimal(181573))
                .build();

        CargoStatisticsDto cargoStatisticsDto = CargoStatisticsDto.builder()
                .id(cargoStatistics.getId())
                .cargoWeightLb(cargoStatistics.getCargoWeightLb())
                .cargoWeightKg(cargoStatistics.getCargoWeightKg())
                .baggageWeightLb(cargoStatistics.getBaggageWeightLb())
                .baggageWeightKg(cargoStatistics.getBaggageWeightKg())
                .totalWeightLb(cargoStatistics.getTotalWeightLb())
                .totalWeightKg(cargoStatistics.getTotalWeightKg())
                .build();

        int flightNumber = 8300;

        when(cargoStatisticsService.getStatistics(flightNumber)).thenReturn(cargoStatistics);
        when(cargoStatisticsMapper.mapToCargoStatisticsDto(cargoStatistics)).thenReturn(cargoStatisticsDto);
        when(flightNumberValidator.validateFlightNumber(flightNumber)).thenReturn(flightNumber);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/cargosStatistics/getStatistics?flightNumber=" + flightNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
