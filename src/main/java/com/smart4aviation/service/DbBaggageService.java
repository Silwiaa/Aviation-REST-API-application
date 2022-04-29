package com.smart4aviation.service;

import com.smart4aviation.domain.Baggage;
import com.smart4aviation.exception.BaggageNotFoundException;
import com.smart4aviation.repository.BaggageRepository;
import com.smart4aviation.validator.FlightNumberValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DbBaggageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbBaggageService.class);
    private final BaggageRepository baggageRepository;

    public Baggage findBaggageById(long id) throws BaggageNotFoundException {
        return baggageRepository.findById(id).orElseThrow(BaggageNotFoundException::new);
    }

    public List<Baggage> findBaggageList(List<Long> baggageIdList) {
        return baggageIdList.stream()
                .map(b -> {
                    try {
                        return findBaggageById(b);
                    } catch (BaggageNotFoundException e) {
                        LOGGER.info(e.getMessage());
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}
