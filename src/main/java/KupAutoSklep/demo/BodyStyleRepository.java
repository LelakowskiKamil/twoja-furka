package KupAutoSklep.demo;

import KupAutoSklep.demo.model.BodyStyle;
import KupAutoSklep.demo.model.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyStyleRepository  extends JpaRepository<BodyStyle, Integer> {
    List<BodyStyle> findAll();
}
