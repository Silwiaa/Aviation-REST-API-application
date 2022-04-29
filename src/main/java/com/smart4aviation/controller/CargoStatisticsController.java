package com.smart4aviation.controller;

import com.smart4aviation.exception.InvalidFlightNumberException;
import com.smart4aviation.exception.CargoEntityNotFoundException;
import com.smart4aviation.exception.FlightEntityNotFoundException;
import com.smart4aviation.domain.CargoStatisticsDto;
import com.smart4aviation.mapper.CargoStatisticsMapper;
import com.smart4aviation.service.DbCargoStatisticsService;
import com.smart4aviation.validator.FlightNumberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/cargosStatistics")
@RequiredArgsConstructor
public class CargoStatisticsController {
    private final DbCargoStatisticsService dbCargoStatisticsService;
    private final CargoStatisticsMapper cargoStatisticsMapper;
    private final FlightNumberValidator flightNumberValidator;

    @GetMapping("/getStatistics")
    public ResponseEntity<CargoStatisticsDto> getStatistics(@RequestParam int flightNumber) throws InvalidFlightNumberException, CargoEntityNotFoundException, FlightEntityNotFoundException {
        return ResponseEntity.ok(cargoStatisticsMapper.mapToCargoStatisticsDto(dbCargoStatisticsService.getStatistics(flightNumberValidator.validateFlightNumber(flightNumber))));
    }
}