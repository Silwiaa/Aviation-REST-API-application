package com.smart4aviation.validator;

import com.smart4aviation.exception.InvalidDateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateValidator.class);

    public String validateDate(final String date) throws InvalidDateException {
        String delimiter = "-";
        if (date.length() != 10) {
            LOGGER.error("Invalid date");
            throw new InvalidDateException();
        }

        validateString(date.substring(0, 4));
        validateString(date.substring(5, 7));
        validateString(date.substring(8, 10));

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        LocalDate localDate = LocalDate.of(year, month, day);

        if (
                !date.substring(4, 5).equals(delimiter) ||
                !date.substring(7, 8).equals(delimiter) ||
                localDate.isBefore(LocalDate.of(1903, 1, 1)) ||
                localDate.isAfter(LocalDate.now().plusYears(50))
        ) {
            LOGGER.error("Invalid date");
            throw new InvalidDateException();
        }
        return date;
    }

    private void validateString(String validatedString) throws InvalidDateException {
        for (char c : validatedString.toCharArray()) {
            if (!Character.isDigit(c)) {
                LOGGER.error("Invalid date");
                throw new InvalidDateException();
            }
        }
    }
}
