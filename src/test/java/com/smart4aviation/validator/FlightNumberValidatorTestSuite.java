package com.smart4aviation.validator;

import com.smart4aviation.exception.InvalidFlightNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FlightNumberValidatorTestSuite {
    @InjectMocks
    private FlightNumberValidator flightNumberValidator;

    @Test
    public void shouldFetchValidFlightNumber() throws InvalidFlightNumberException {
        //Given
        int flightNumber = 8300;

        //When
        int resultFlightNumber = flightNumberValidator.validateFlightNumber(flightNumber);
        
        //Then
        assertEquals(resultFlightNumber, flightNumber);
    }
}
