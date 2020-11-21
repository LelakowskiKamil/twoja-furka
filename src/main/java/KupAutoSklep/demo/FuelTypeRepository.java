package KupAutoSklep.demo;

import KupAutoSklep.demo.model.CarModel;
import KupAutoSklep.demo.model.FuelType;
import KupAutoSklep.demo.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Integer> {
    List<FuelType> findAll();
}
