package com.smart4aviation.repository;

import com.smart4aviation.domain.FlightStatistics;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightStatisticRepositoryTestSuite {
    @Autowired
    private FlightStatisticsRepository flightStatisticsRepository;

    @Test
    public void TestSaveFlightStatistic() {
        //Given
        FlightStatistics flightStatistics = FlightStatistics.builder()
                .id(1L)
                .flightsDeparting(10)
                .flightsArriving(5)
                .totalBaggageArriving(45)
                .totalBaggageDeparting(15)
                .build();

        long flightStatisticId = flightStatistics.getId();

        //When
        flightStatisticsRepository.save(flightStatistics);

        //Then
        assertTrue(flightStatisticsRepository.existsById(flightStatisticId));

        //CleanUp
        flightStatisticsRepository.deleteById(flightStatisticId);
    }
}
