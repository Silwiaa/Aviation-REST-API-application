package com.smart4aviation.mapper;

import com.smart4aviation.domain.Cargo;
import com.smart4aviation.domain.CargoDto;
import com.smart4aviation.service.DbCargoEntityService;
import com.smart4aviation.service.DbCargoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoMapper {
    public Cargo mapToCargo(CargoDto cargoDto) {
        return Cargo.builder()
                .id(cargoDto.getId())
                .totalWeight(cargoDto.getWeight())
                .weightUnit(cargoDto.getWeightUnit())
                .pieces(cargoDto.getPieces())
                .build();
    }
}
