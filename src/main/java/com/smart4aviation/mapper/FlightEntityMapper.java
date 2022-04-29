package com.smart4aviation.mapper;

import com.smart4aviation.domain.FlightEntity;
import com.smart4aviation.domain.FlightEntityDto;
import com.smart4aviation.parser.DateParser;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightEntityMapper {
    private final DateParser dateParser;

    public FlightEntity mapToFlightEntity(FlightEntityDto flightEntityDto) {
        return FlightEntity.builder()
                .flightId(flightEntityDto.getFlightId())
                .flightNumber(flightEntityDto.getFlightNumber())
                .departureAirportIATACode(flightEntityDto.getDepartureAirportIATACode())
                .arrivalAirportIATACode(flightEntityDto.getArrivalAirportIATACode())
                .departureDate(dateParser.parseDateTime(flightEntityDto.getDepartureDate()))
                .arrivalDate(dateParser.parseDateTime(flightEntityDto.getArrivalDate()))
                .build();
    }
}
