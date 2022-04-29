package com.smart4aviation.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart4aviation.domain.FlightEntity;
import com.smart4aviation.domain.FlightEntityDto;
import com.smart4aviation.exception.FlightEntityNotFoundException;
import com.smart4aviation.fileReader.JSONReader;
import com.smart4aviation.mapper.FlightEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightEntityRepositoryTestSuite {
    @Autowired
    private FlightEntityRepository flightEntityRepository;

    @Autowired
    private JSONReader jsonReader;

    @Autowired
    private FlightEntityMapper flightEntityMapper;

    @Test
    public void testSaveFlightEntity() throws JsonProcessingException {
        //Given
        ObjectMapper objectMapper = new ObjectMapper();
        FlightEntityDto flightEntityDto = objectMapper.readValue(jsonReader.readFile("JSONFiles/FlightEntityDto.json"), FlightEntityDto.class);
        FlightEntity flightEntity = flightEntityMapper.mapToFlightEntity(flightEntityDto);
        long flightEntityId = flightEntity.getFlightId();

        //When
        flightEntityRepository.save(flightEntity);

        //Then
        assertTrue(flightEntityRepository.existsById(flightEntityId));

        //CleanUp
        flightEntityRepository.deleteById(flightEntityId);
    }

    @Test
    public void testFindAllFlightEntitiesByDepartureAirportIATACode() throws JsonProcessingException {
        //Given
        ObjectMapper objectMapper = new ObjectMapper();
        FlightEntityDto flightEntityDto1 = objectMapper.readValue(jsonReader.readFile("JSONFiles/FlightEntityDto.json"), FlightEntityDto.class);
        FlightEntity flightEntity1 = flightEntityMapper.mapToFlightEntity(flightEntityDto1);
        flightEntityRepository.save(flightEntity1);
        long flightEntityId1 = flightEntity1.getFlightId();

        FlightEntityDto flightEntityDto2 = objectMapper.readValue(jsonReader.readFile("JSONFiles/FlightEntityDto.json"), FlightEntityDto.class);
        flightEntityDto2.setFlightId(2L);
        flightEntityDto2.setFlightNumber(8344);
        FlightEntity flightEntity2 = flightEntityMapper.mapToFlightEntity(flightEntityDto2);
        flightEntityRepository.save(flightEntity2);
        long flightEntityId2 = flightEntity2.getFlightId();

        FlightEntityDto flightEntityDto3 = objectMapper.readValue(jsonReader.readFile("JSONFiles/FlightEntityDto.json"), FlightEntityDto.class);
        flightEntityDto3.setFlightId(3L);
        flightEntityDto3.setFlightNumber(9344);
        flightEntityDto3.setDepartureAirportIATACode("MIT");
        flightEntityDto3.setArrivalAirportIATACode("YYT");
        FlightEntity flightEntity3 = flightEntityMapper.mapToFlightEntity(flightEntityDto3);
        flightEntityRepository.save(flightEntity3);
        long flightEntityId3 = flightEntity3.getFlightId();

        //When
        List<FlightEntity> resultFlightEntityList = flightEntityRepository.findAllByDepartureAirportIATACode("YYT");

        //Then
        assertEquals(resultFlightEntityList.size(), 2);
        assertEquals(resultFlightEntityList.get(0).getDepartureAirportIATACode(), "YYT");
        assertEquals(resultFlightEntityList.get(1).getDepartureAirportIATACode(), "YYT");

        //CleanUp
        flightEntityRepository.deleteById(flightEntityId1);
        flightEntityRepository.deleteById(flightEntityId2);
        flightEntityRepository.deleteById(flightEntityId3);
    }

    @Test
    public void testFindAllFlightEntitiesByArrivalAirportIATACode() throws JsonProcessingException {
        //Given
        ObjectMapper objectMapper = new ObjectMapper();
        FlightEntityDto flightEntityDto1 = objectMapper.readValue(jsonReader.readFile("JSONFiles/FlightEntityDto.json"), FlightEntityDto.class);
        FlightEntity flightEntity1 = flightEntityMapper.mapToFlightEntity(flightEntityDto1);
        flightEntityRepository.save(flightEntity1);
        long flightEntityId1 = flightEntity1.getFlightId();

        FlightEntityDto flightEntityDto2 = objectMapper.readValue(jsonReader.readFile("JSONFiles/FlightEntityDto.json"), FlightEntityDto.class);
        flightEntityDto2.setFlightId(2L);
        flightEntityDto2.setFlightNumber(8344);
        FlightEntity flightEntity2 = flightEntityMapper.mapToFlightEntity(flightEntityDto2);
        flightEntityRepository.save(flightEntity2);
        long flightEntityId2 = flightEntity2.getFlightId();

        FlightEntityDto flightEntityDto3 = objectMapper.readValue(jsonReader.readFile("JSONFiles/FlightEntityDto.json"), FlightEntityDto.class);
        flightEntityDto3.setFlightId(3L);
        flightEntityDto3.setFlightNumber(9344);
        flightEntityDto3.setDepartureAirportIATACode("MIT");
        flightEntityDto3.setArrivalAirportIATACode("YYT");
        FlightEntity flightEntity3 = flightEntityMapper.mapToFlightEntity(flightEntityDto3);
        flightEntityRepository.save(flightEntity3);
        long flightEntityId3 = flightEntity3.getFlightId();

        //When
        List<FlightEntity> resultFlightEntityList = flightEntityRepository.findAllByArrivalAirportIATACode("MIT");

        //Then
        assertEquals(resultFlightEntityList.size(), 2);
        assertEquals(resultFlightEntityList.get(0).getArrivalAirportIATACode(), "MIT");
        assertEquals(resultFlightEntityList.get(1).getArrivalAirportIATACode(), "MIT");

        //CleanUp
        flightEntityRepository.deleteById(flightEntityId1);
        flightEntityRepository.deleteById(flightEntityId2);
        flightEntityRepository.deleteById(flightEntityId3);
    }

    @Test
    public void findFlightEntityByFlightNumber() throws JsonProcessingException, FlightEntityNotFoundException {
        //Given
        ObjectMapper objectMapper = new ObjectMapper();
        FlightEntityDto flightEntityDto1 = objectMapper.readValue(jsonReader.readFile("JSONFiles/FlightEntityDto.json"), FlightEntityDto.class);
        FlightEntity flightEntity1 = flightEntityMapper.mapToFlightEntity(flightEntityDto1);
        flightEntityRepository.save(flightEntity1);
        long flightEntityId1 = flightEntity1.getFlightId();

        //When
        FlightEntity resultFlightEntity = flightEntityRepository.findByFlightNumber(4962).orElseThrow(FlightEntityNotFoundException::new);

        //Then
        assertEquals(resultFlightEntity.getFlightNumber(), 4962);

        //CleanUp
        flightEntityRepository.deleteById(flightEntityId1);
    }
}
