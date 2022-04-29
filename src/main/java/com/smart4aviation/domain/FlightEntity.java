package com.smart4aviation.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "FLIGHTS_ENTITY")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightEntity {
    @Id
    @NotNull
    @Column(name = "FLIGHT_ID", unique = true)
    private long flightId;

    @NotNull
    @Column(name = "FLIGHTS_NUMBER")
    private int flightNumber;

    @NotNull
    @Column(name = "DEPARTURE_AIRPORT_IATA_CODE")
    private String departureAirportIATACode;

    @NotNull
    @Column(name = "ARRIVAL_AIRPORT_IATA_CODE")
    private String arrivalAirportIATACode;

    @NotNull
    @Column(name = "DEPARTURE_DATE")
    private LocalDateTime departureDate;

    @NotNull
    @Column(name = "ARRIVAL_DATE")
    private LocalDateTime arrivalDate;
}
