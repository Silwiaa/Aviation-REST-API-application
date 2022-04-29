package com.smart4aviation.validator;

import com.smart4aviation.exception.InvalidFlightNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FlightNumberValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightNumberValidator.class);

    public int validateFlightNumber(final int flightNumber) throws InvalidFlightNumberException {
        if (flightNumber < 1000 || flightNumber > 9999) {
            LOGGER.error("Invalid flight number");
            throw new InvalidFlightNumberException();
        }
        return flightNumber;
    }
}
