package com.lelakowsky.twojafurka.service.impl;

import com.lelakowsky.twojafurka.domain.car.CarManufacturer;
import com.lelakowsky.twojafurka.domain.car.CarModel;
import com.lelakowsky.twojafurka.repository.CarModelRepository;
import com.lelakowsky.twojafurka.service.CarModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarModelServiceImpl implements CarModelService {

    private final CarModelRepository carModelRepository;


    public List<CarModel> getCarModelByManufacturer(CarManufacturer carManufacturer) {
        return carModelRepository.getCarModelsByManufacturerId(carManufacturer.getId());
    }

    public List<CarModel> getCarModelByManufacturerId(Integer carManufacturerId) {
        return carModelRepository.getCarModelsByManufacturerId(carManufacturerId);
    }

    public CarModel getCarModel(Integer id) {
        return carModelRepository.getById(id);
    }

    public List<CarModel> getCarModels() {
        return carModelRepository.findAll();
    }


}
