package KupAutoSklep.demo;

import KupAutoSklep.demo.model.BodyStyle;
import KupAutoSklep.demo.model.CarManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarManufacturerRepository extends JpaRepository<CarManufacturer, Integer> {
    List<CarManufacturer> findAll();
}
