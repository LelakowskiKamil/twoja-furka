package KupAutoSklep.demo.domain.repository;

import KupAutoSklep.demo.domain.model.BodyStyle;
import KupAutoSklep.demo.domain.model.CarManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarManufacturerRepository extends JpaRepository<CarManufacturer, Integer> {
    List<CarManufacturer> findAll();
}
