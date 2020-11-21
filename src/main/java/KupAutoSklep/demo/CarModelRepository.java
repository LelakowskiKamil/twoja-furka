package KupAutoSklep.demo;

import KupAutoSklep.demo.model.CarModel;
import KupAutoSklep.demo.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Integer> {
    List<CarModel> findCarModelsByManufacturer_Id(int manufacturerId);

    List<CarModel> findAll();
}
