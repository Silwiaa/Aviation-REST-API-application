package com.smart4aviation.mapper;

import com.smart4aviation.domain.CargoEntity;
import com.smart4aviation.domain.CargoEntityDto;
import com.smart4aviation.exception.FlightEntityNotFoundException;
import com.smart4aviation.service.DbBaggageService;
import com.smart4aviation.service.DbCargoService;
import com.smart4aviation.service.DbFlightEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CargoEntityMapper {
    private final DbFlightEntityService flightEntityService;
    private final DbBaggageService dbBaggageService;
    private final DbCargoService dbCargoService;

    public CargoEntity mapToCargoEntity(CargoEntityDto cargoEntityDto) throws FlightEntityNotFoundException {
        return CargoEntity.builder()
                .cargoEntityId(cargoEntityDto.getCargoId())
                .flightEntity(flightEntityService.findFlight(cargoEntityDto.getFlightEntityId()))
                .baggageList(dbBaggageService.findBaggageList(cargoEntityDto.getBaggageIdList()))
                .cargoList(dbCargoService.findCargoList(cargoEntityDto.getCargoIdList()))
                .build();
    }
}
