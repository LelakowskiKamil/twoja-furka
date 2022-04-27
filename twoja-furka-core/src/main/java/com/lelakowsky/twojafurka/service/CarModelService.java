package com.lelakowsky.twojafurka.service;

import com.lelakowsky.twojafurka.domain.car.CarManufacturer;
import com.lelakowsky.twojafurka.domain.car.CarModel;
import java.util.List;

public interface CarModelService {

    List<CarModel> getCarModelByManufacturer(CarManufacturer carManufacturer);

    List<CarModel> getCarModelByManufacturerId(Integer carManufacturerId);

    CarModel getCarModel(Integer id);

    List<CarModel> getCarModels();


}
