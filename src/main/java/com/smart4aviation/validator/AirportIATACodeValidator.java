package com.smart4aviation.validator;

import com.smart4aviation.exception.InvalidAirportIATACodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AirportIATACodeValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(AirportIATACodeValidator.class);

    public String validateAirportIATACode(final String airportIATACode) throws InvalidAirportIATACodeException {
        if (airportIATACode.length() != 3) {
            LOGGER.error("Invalid airportIATAcode");
            throw new InvalidAirportIATACodeException();
        }
        return airportIATACode;
    }
}