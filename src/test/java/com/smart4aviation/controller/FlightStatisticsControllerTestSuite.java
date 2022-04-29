package com.smart4aviation.controller;

import com.smart4aviation.domain.FlightStatistics;
import com.smart4aviation.domain.FlightStatisticsDto;
import com.smart4aviation.exception.CargoEntityNotFoundException;
import com.smart4aviation.exception.FlightEntityNotFoundException;
import com.smart4aviation.mapper.FlightStatisticsMapper;
import com.smart4aviation.parser.DateParser;
import com.smart4aviation.service.DbFlightStatisticsService;
import com.smart4aviation.validator.AirportIATACodeValidator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(FlightStatisticsController.class)
public class FlightStatisticsControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbFlightStatisticsService flightStatisticsService;

    @MockBean
    private FlightStatisticsMapper flightStatisticsMapper;

    @MockBean
    private AirportIATACodeValidator airportIATACodeValidator;

    @MockBean
    private DateParser dateParser;

    @SneakyThrows
    @Test
    void shouldFetchFlightStatistics() throws CargoEntityNotFoundException, FlightEntityNotFoundException {
        //Given
        FlightStatistics flightStatistics = FlightStatistics.builder()
                .id(1L)
                .flightsDeparting(2)
                .flightsArriving(5)
                .totalBaggageDeparting(7)
                .totalBaggageArriving(6)
                .build();

        FlightStatisticsDto flightStatisticsDto = FlightStatisticsDto.builder()
                .id(flightStatistics.getId())
                .flightsDeparting(flightStatistics.getFlightsDeparting())
                .flightsArriving(flightStatistics.getFlightsArriving())
                .totalBaggageDeparting(flightStatistics.getTotalBaggageDeparting())
                .totalBaggageArriving(flightStatistics.getTotalBaggageArriving())
                .build();

        String airportIATACode = "XYZ";
        String date = "2022-12-24";

        when(flightStatisticsService.getStatistics("YYT", LocalDateTime.of(2022, 12, 24, 0, 0))).thenReturn(flightStatistics);
        when(flightStatisticsMapper.mapToFlightStatisticsDto(flightStatistics)).thenReturn(flightStatisticsDto);
        when(airportIATACodeValidator.validateAirportIATACode(airportIATACode)).thenReturn(airportIATACode);
        when(dateParser.parseDate(date)).thenReturn(LocalDateTime.of(2022, 12, 24, 0, 0));

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/flightsStatistics/getStatistics?airportIATACode=" + airportIATACode + "&date=" + date)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
