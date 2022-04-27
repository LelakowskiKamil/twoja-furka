package com.lelakowsky.twojafurka.service;

import com.lelakowsky.twojafurka.domain.car.CarManufacturer;

import java.util.List;

public interface CarManufacturerService {

    List<String> getCarManufacturersNames();

    List<CarManufacturer> getCarManufacturers();

}
