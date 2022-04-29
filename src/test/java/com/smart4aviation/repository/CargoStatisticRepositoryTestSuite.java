package com.smart4aviation.repository;

import com.smart4aviation.domain.CargoStatistics;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CargoStatisticRepositoryTestSuite {
    @Autowired
    private CargoStatisticsRepository cargoStatisticsRepository;

    @Test
    public void testSaveCargoStatistic() {
        //Given
        CargoStatistics cargoStatistics = CargoStatistics.builder()
                .id(1L)
                .cargoWeightLb(new BigDecimal(400000))
                .cargoWeightKg(new BigDecimal(181437))
                .baggageWeightLb(new BigDecimal(300))
                .baggageWeightKg(new BigDecimal(136))
                .totalWeightLb(new BigDecimal(400300))
                .totalWeightKg(new BigDecimal(181573))
                .build();

        long cargoStatisticsId = cargoStatistics.getId();

        //When
        cargoStatisticsRepository.save(cargoStatistics);

        //Then
        assertTrue(cargoStatisticsRepository.existsById(cargoStatisticsId));

        //Cleanup
        cargoStatisticsRepository.deleteById(cargoStatisticsId);
    }
}
