package com.smart4aviation.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart4aviation.domain.Baggage;
import com.smart4aviation.domain.BaggageDto;
import com.smart4aviation.fileReader.JSONReader;
import com.smart4aviation.mapper.BaggageMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaggageRepositoryTestSuite {
    @Autowired
    private BaggageRepository baggageRepository;

    @Autowired
    private JSONReader jsonReader;

    @Autowired
    private BaggageMapper baggageMapper;

    @Test
    public void testSaveBaggage() throws JsonProcessingException {
        //Given
        ObjectMapper objectMapper = new ObjectMapper();
        BaggageDto baggageDto = objectMapper.readValue(jsonReader.readFile("JSONFiles/BaggageDto.json"), BaggageDto.class);
        Baggage baggage = baggageMapper.mapToBaggage(baggageDto);
        Long id = baggage.getId();

        //When
        baggageRepository.save(baggage);

        //Then
        assertTrue(baggageRepository.existsById(id));

        //CleanUp
        baggageRepository.deleteById(id);
    }
}
