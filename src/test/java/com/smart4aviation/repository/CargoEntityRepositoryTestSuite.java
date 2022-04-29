package com.smart4aviation.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart4aviation.domain.*;
import com.smart4aviation.exception.FlightEntityNotFoundException;
import com.smart4aviation.fileReader.JSONReader;
import com.smart4aviation.mapper.BaggageMapper;
import com.smart4aviation.mapper.CargoEntityMapper;
import com.smart4aviation.mapper.CargoMapper;
import com.smart4aviation.mapper.FlightEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CargoEntityRepositoryTestSuite {
    @Autowired
    private CargoEntityRepository cargoEntityRepository;

    @Autowired
    private FlightEntityRepository flightEntityRepository;

    @Autowired
    private BaggageRepository baggageRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private JSONReader jsonReader;

    @Autowired
    private FlightEntityMapper flightEntityMapper;

    @Autowired
    private CargoMapper cargoMapper;

    @Autowired
    private BaggageMapper baggageMapper;

    @Autowired
    private CargoEntityMapper cargoEntityMapper;

    @Test
    public void testSaveCargoEntity() throws Exception {
        //Given
        ObjectMapper objectMapper = new ObjectMapper();
        FlightEntityDto flightEntityDto = objectMapper.readValue(jsonReader.readFile("JSONFiles/FlightEntityDto.json"), FlightEntityDto.class);
        FlightEntity flightEntity = flightEntityMapper.mapToFlightEntity(flightEntityDto);
        flightEntityRepository.save(flightEntity);
        long flightEntityId = flightEntity.getFlightId();

        CargoDto cargoDto = objectMapper.readValue(jsonReader.readFile("JSONFiles/CargoDto.json"), CargoDto.class);
        Cargo cargo = cargoMapper.mapToCargo(cargoDto);
        cargoRepository.save(cargo);
        long cargoId = cargo.getId();

        BaggageDto baggageDto = objectMapper.readValue(jsonReader.readFile("JSONFiles/BaggageDto.json"), BaggageDto.class);
        Baggage baggage = baggageMapper.mapToBaggage(baggageDto);
        baggageRepository.save(baggage);
        long baggageId = baggage.getId();

        CargoEntityDto cargoEntityDto = objectMapper.readValue(jsonReader.readFile("JSONFiles/CargoEntityDto.json"), CargoEntityDto.class);
        CargoEntity cargoEntity = cargoEntityMapper.mapToCargoEntity(cargoEntityDto);

        //When
        cargoEntityRepository.save(cargoEntity);
        long cargoEntityId = cargoEntity.getCargoEntityId();

        //Then
        assertTrue(cargoEntityRepository.existsById(cargoEntityId));

        //CleanUp
        cargoRepository.deleteById(cargoId);
        baggageRepository.deleteById(baggageId);
        flightEntityRepository.deleteById(flightEntityId);
        cargoEntityRepository.deleteById(cargoEntityId);
    }

    @Test
    public void testFindCargoEntityByFlightEntity() throws JsonProcessingException, FlightEntityNotFoundException {
        //Given
        ObjectMapper objectMapper = new ObjectMapper();
        FlightEntityDto flightEntityDto = objectMapper.readValue(jsonReader.readFile("JSONFiles/FlightEntityDto.json"), FlightEntityDto.class);
        FlightEntity flightEntity = flightEntityMapper.mapToFlightEntity(flightEntityDto);
        flightEntityRepository.save(flightEntity);
        long flightEntityId = flightEntity.getFlightId();

        CargoEntityDto cargoEntityDto = objectMapper.readValue(jsonReader.readFile("JSONFiles/CargoEntityDto.json"), CargoEntityDto.class);
        CargoEntity cargoEntity = cargoEntityMapper.mapToCargoEntity(cargoEntityDto);
        cargoEntityRepository.save(cargoEntity);
        long cargoEntityId = cargoEntity.getCargoEntityId();

        //When
        CargoEntity resultCargoEntity = cargoEntityRepository.findByFlightEntity(flightEntity).orElseThrow(FlightEntityNotFoundException::new);

        //Then
        assertEquals(resultCargoEntity.getFlightEntity().getFlightId(), flightEntity.getFlightId());

        //CleanUp
        flightEntityRepository.deleteById(flightEntityId);
        cargoEntityRepository.deleteById(cargoEntityId);
    }

}