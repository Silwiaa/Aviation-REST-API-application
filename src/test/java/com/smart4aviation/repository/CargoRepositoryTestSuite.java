package com.smart4aviation.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart4aviation.domain.Cargo;
import com.smart4aviation.domain.CargoDto;
import com.smart4aviation.fileReader.JSONReader;
import com.smart4aviation.mapper.CargoMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CargoRepositoryTestSuite {
    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private JSONReader jsonReader;

    @Autowired
    private CargoMapper cargoMapper;

    @Test
    public void testSaveCargo() throws JsonProcessingException {
        //Given
        ObjectMapper objectMapper = new ObjectMapper();
        CargoDto cargoDto = objectMapper.readValue(jsonReader.readFile("JSONFiles/CargoDto.json"), CargoDto.class);
        Cargo cargo = cargoMapper.mapToCargo(cargoDto);
        Long id = cargo.getId();

        //When
        cargoRepository.save(cargo);

        //Then
        assertTrue(cargoRepository.existsById(id));

        //CleanUp
        cargoRepository.deleteById(id);
    }
}
