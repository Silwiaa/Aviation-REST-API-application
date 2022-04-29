package com.smart4aviation.controller;

import com.smart4aviation.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BaggageNotFoundException.class)
    public ResponseEntity<Object> handleBaggageNotFoundException(BaggageNotFoundException baggageNotFoundException) {
        return new ResponseEntity<>("Baggage with given id doesn't exist or can't be found in repository", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlightEntityNotFoundException.class)
    public ResponseEntity<Object> handleFlightEntityNotFoundException(FlightEntityNotFoundException flightEntityNotFoundException) {
        return new ResponseEntity<>("FlightEntity with given id or flightNumber doesn't exist or can't be found in repository", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CargoEntityNotFoundException.class)
    public ResponseEntity<Object> handleCargoEntityoundException(CargoEntityNotFoundException cargoEntityNotFoundException) {
        return new ResponseEntity<>("CargoEntity with given id or flightEntity doesn't exist or can't be found in repository", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CargoNotFoundException.class)
    public ResponseEntity<Object> handleCargoNotFoundException(CargoNotFoundException cargoNotFoundException) {
        return new ResponseEntity<>("Cargo with given id doesn't exist or can't be found in repository", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidAirportIATACodeException.class)
    public ResponseEntity<Object> handleInvalidAirportIATACodeException(InvalidAirportIATACodeException invalidAirportIATACodeException) {
        return new ResponseEntity<>("Invalid airportIATACode", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidFlightNumberException.class)
    public ResponseEntity<Object> handleInvalidFlightNumberException(InvalidFlightNumberException invalidFlightNumberException) {
        return new ResponseEntity<>("Invalid flightNumber", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<Object> handleInvalidDateException(InvalidDateException invalidDateException) {
        return new ResponseEntity<>("Invalid date", HttpStatus.NOT_ACCEPTABLE);
    }
}
