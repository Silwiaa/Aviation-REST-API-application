package com.smart4aviation.service;

import com.smart4aviation.domain.CargoEntity;
import com.smart4aviation.domain.FlightEntity;
import com.smart4aviation.exception.CargoEntityNotFoundException;
import com.smart4aviation.exception.FlightEntityNotFoundException;
import com.smart4aviation.repository.CargoEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DbCargoEntityService {
    private final CargoEntityRepository cargoEntityRepository;
    private final DbFlightEntityService dbFlightEntityService;

    public List<CargoEntity> findDepartingCargos(final String departureAirportIATACode, final LocalDateTime departureDate) throws CargoEntityNotFoundException, FlightEntityNotFoundException {
        List<FlightEntity> departingFlightEntityList = dbFlightEntityService.findDepartingFlights(departureAirportIATACode, departureDate);
        return getCargoEntityList(departingFlightEntityList);
    }

    public List<CargoEntity> findArrivingCargos(final String arrivalAirportIATACode, final LocalDateTime arrivalDate) throws CargoEntityNotFoundException, FlightEntityNotFoundException {
        List<FlightEntity> arrivingFlightEntityList = dbFlightEntityService.findArrivingFlights(arrivalAirportIATACode, arrivalDate);
        return getCargoEntityList(arrivingFlightEntityList);

    }

    public CargoEntity findCargoEntity(final int flightNumber) throws FlightEntityNotFoundException, CargoEntityNotFoundException {
        return cargoEntityRepository.findByFlightEntity(dbFlightEntityService.findFlightByFlightNumber(flightNumber)).orElseThrow(CargoEntityNotFoundException::new);
    }

    private List<CargoEntity> getCargoEntityList(List<FlightEntity> flightEntityList) throws FlightEntityNotFoundException, CargoEntityNotFoundException {
        List<Long> flightIdList = flightEntityList.stream()
                .map(FlightEntity::getFlightId)
                .collect(Collectors.toList());
        List<CargoEntity> cargoEntityList = new ArrayList<>();

        for (long flightId : flightIdList) {
            FlightEntity flightEntity = dbFlightEntityService.findFlight(flightId);
            cargoEntityList.add(findCargoEntityByFlightEntity(flightEntity));
        }

        return cargoEntityList;
    }

    public CargoEntity findCargoEntityByFlightEntity(FlightEntity flightEntity) throws CargoEntityNotFoundException {
        return cargoEntityRepository.findByFlightEntity(flightEntity).orElseThrow(CargoEntityNotFoundException::new);
    }

    public CargoEntity findCargoEntityById(long cargoId) throws CargoEntityNotFoundException {
        return cargoEntityRepository.findById(cargoId).orElseThrow(CargoEntityNotFoundException::new);
    }
}
