package com.smart4aviation.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "FLIGHT_STATISTICS")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightStatistics {
    @Id
    @NotNull
    @Column(name = "ID", unique = true)
    private long id;

    @NotNull
    @Column(name = "FLIGHTS_DEPARTING")
    private int flightsDeparting;

    @NotNull
    @Column(name = "FLIGHTS_ARRIVING")
    private int flightsArriving;

    @NotNull
    @Column(name = "TOTAL_BAGGAGE_DEPARTING")
    private int totalBaggageDeparting;

    @NotNull
    @Column(name = "TOTAL_BAGGAGE_ARRIVING")
    private int totalBaggageArriving;
}
