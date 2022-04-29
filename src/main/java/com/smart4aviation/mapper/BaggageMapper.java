package com.smart4aviation.mapper;

import com.smart4aviation.domain.Baggage;
import com.smart4aviation.domain.BaggageDto;
import com.smart4aviation.service.DbBaggageService;
import com.smart4aviation.service.DbCargoEntityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaggageMapper {
    public Baggage mapToBaggage(BaggageDto baggageDto) {
        return Baggage.builder()
                .id(baggageDto.getId())
                .totalWeight(baggageDto.getWeight())
                .weightUnit(baggageDto.getWeightUnit())
                .pieces(baggageDto.getPieces())
                .build();
    }
}