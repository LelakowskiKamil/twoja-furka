package com.lelakowsky.twojafurka.repository;

import com.lelakowsky.twojafurka.domain.car.CarManufacturer;
import com.lelakowsky.twojafurka.domain.car.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Integer> {

    @Query(value = "SELECT c FROM CarModel c WHERE c.manufacturer.id = ?1")
    List<CarModel> getCarModelsByManufacturerId(Integer carManufacturerId);
}
