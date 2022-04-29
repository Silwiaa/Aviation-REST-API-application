package com.smart4aviation.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WeightUnitMapperTestSuite {
    @InjectMocks
    private WeightUnitMapper weightUnitMapper;

    @Test
    public void testMapToLb() {
        //Given
        double kg = 1.00;

        //When
        double lb = weightUnitMapper.mapToLb(kg);

        //Then
        assertEquals(lb, 2.20462262, 0.00000001);
    }

    @Test
    public void testMapToKg() {
        //Given
        double lb = 1.00;

        //When
        double kg = weightUnitMapper.mapToKg(lb);

        //Then
        assertEquals(kg, 0.45359237, 0.00000001);
    }
}
