package com.smart4aviation.controller;

import com.smart4aviation.exception.InvalidAirportIATACodeException;
import com.smart4aviation.exception.CargoEntityNotFoundException;
import com.smart4aviation.exception.FlightEntityNotFoundException;
import com.smart4aviation.domain.FlightStatisticsDto;
import com.smart4aviation.exception.InvalidDateException;
import com.smart4aviation.mapper.FlightStatisticsMapper;
import com.smart4aviation.parser.DateParser;
import com.smart4aviation.service.DbFlightStatisticsService;
import com.smart4aviation.validator.AirportIATACodeValidator;
import com.smart4aviation.validator.DateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/flightsStatistics")
@RequiredArgsConstructor
public class FlightStatisticsController {
    private final DbFlightStatisticsService dbFlightStatisticsService;
    private final FlightStatisticsMapper flightStatisticsMapper;
    private final AirportIATACodeValidator airportIATACodeValidator;
    private final DateValidator dateValidator;
    private final DateParser dateParser;

    @GetMapping("/getStatistics")
    public ResponseEntity<FlightStatisticsDto> getStatistics(@RequestParam String airportIATACode, @RequestParam String date) throws InvalidAirportIATACodeException, CargoEntityNotFoundException, FlightEntityNotFoundException, InvalidDateException {
        return ResponseEntity.ok(flightStatisticsMapper.mapToFlightStatisticsDto(dbFlightStatisticsService.getStatistics(airportIATACodeValidator.validateAirportIATACode(airportIATACode), dateParser.parseDate(dateValidator.validateDate(date)))));
    }
}