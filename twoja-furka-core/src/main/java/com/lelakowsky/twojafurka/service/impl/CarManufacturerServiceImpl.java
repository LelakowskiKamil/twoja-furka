package com.lelakowsky.twojafurka.service.impl;

import com.lelakowsky.twojafurka.domain.car.CarManufacturer;
import com.lelakowsky.twojafurka.repository.CarManufacturerRepository;
import com.lelakowsky.twojafurka.service.CarManufacturerService;
import com.lelakowsky.twojafurka.service.CarModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarManufacturerServiceImpl implements CarManufacturerService {

    private final CarManufacturerRepository carManufacturerRepository;

    @Override
    public List<String> getCarManufacturersNames(){
        return carManufacturerRepository.findAll().stream()
                .map(CarManufacturer::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarManufacturer> getCarManufacturers() {
        return carManufacturerRepository.findAll();
    }

}
