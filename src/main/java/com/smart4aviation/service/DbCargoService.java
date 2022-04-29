package com.smart4aviation.service;

import com.smart4aviation.domain.Cargo;
import com.smart4aviation.exception.CargoNotFoundException;
import com.smart4aviation.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DbCargoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbCargoService.class);
    private final CargoRepository cargoRepository;

    public Cargo findById(long cargoId) throws CargoNotFoundException {
        return cargoRepository.findById(cargoId).orElseThrow(CargoNotFoundException::new);
    }

    public List<Cargo> findCargoList(List<Long> cargoIdList) {
        return cargoIdList.stream()
                .map(c -> {
                    try {
                        return findById(c);
                    } catch (Exception e) {
                        LOGGER.info(e.getMessage());
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}