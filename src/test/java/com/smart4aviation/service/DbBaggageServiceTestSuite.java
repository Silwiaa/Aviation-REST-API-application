package com.smart4aviation.service;

import com.smart4aviation.domain.Baggage;
import com.smart4aviation.exception.BaggageNotFoundException;
import com.smart4aviation.repository.BaggageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbBaggageServiceTestSuite {
    @InjectMocks
    private DbBaggageService dbBaggageService;

    @Mock
    private BaggageRepository baggageRepository;

    @Test
    public void shouldFindBaggageById() throws BaggageNotFoundException {
        //Given
        long id = 1L;
        Optional<Baggage> baggage = Optional.ofNullable(Baggage.builder().id(id).build());

        when(baggageRepository.findById(id)).thenReturn(baggage);

        //When
        Baggage resultBaggage = dbBaggageService.findBaggageById(id);

        //Then
        assertEquals(resultBaggage.getId(), id);
    }
}
