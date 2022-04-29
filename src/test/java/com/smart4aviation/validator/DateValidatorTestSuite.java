package com.smart4aviation.validator;

import com.smart4aviation.exception.InvalidDateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DateValidatorTestSuite {
    @InjectMocks
    private DateValidator dateValidator;

    @Test
    public void shouldFetchValidDate() throws InvalidDateException {
        //Given
        String date = "2022-12-24";

        //When
        String resultDate = dateValidator.validateDate(date);

        //Then
        assertEquals(resultDate, date);
    }
}
