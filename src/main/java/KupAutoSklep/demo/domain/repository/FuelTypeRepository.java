package KupAutoSklep.demo.domain.repository;

import KupAutoSklep.demo.domain.model.CarModel;
import KupAutoSklep.demo.domain.model.FuelType;
import KupAutoSklep.demo.domain.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Integer> {
    List<FuelType> findAll();
}
