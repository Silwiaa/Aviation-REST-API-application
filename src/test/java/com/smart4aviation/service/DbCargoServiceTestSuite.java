package com.smart4aviation.service;

import com.smart4aviation.domain.Cargo;
import com.smart4aviation.exception.CargoNotFoundException;
import com.smart4aviation.repository.CargoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbCargoServiceTestSuite {
    @InjectMocks
    private DbCargoService dbCargoService;

    @Mock
    private CargoRepository cargoRepository;

    @Test
    public void shouldFindCargoById() throws CargoNotFoundException {
        //Given
        Long cargoId = 1L;
        Optional<Cargo> cargo = Optional.ofNullable(Cargo.builder().id(cargoId).build());

        when(cargoRepository.findById(cargoId)).thenReturn(cargo);

        //When
        Cargo resultCargo = dbCargoService.findById(cargoId);

        //Then
        assertEquals(resultCargo.getId(), cargoId);
    }
}
