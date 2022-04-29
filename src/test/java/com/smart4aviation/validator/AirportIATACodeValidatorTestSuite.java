package com.smart4aviation.validator;

import com.smart4aviation.exception.InvalidAirportIATACodeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AirportIATACodeValidatorTestSuite {
    @InjectMocks
    private AirportIATACodeValidator airportIATACodeValidator;

    @Test
    public void shouldFetchValidIATACode() throws InvalidAirportIATACodeException {
        //Given
        String airportIATACode = "SEA";

        //When
        String resultairportIATACode = airportIATACodeValidator.validateAirportIATACode(airportIATACode);

        //Then
        assertEquals(resultairportIATACode, airportIATACode);
    }
}
