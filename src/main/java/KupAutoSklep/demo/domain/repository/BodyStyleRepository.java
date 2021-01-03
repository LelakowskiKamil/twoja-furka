package KupAutoSklep.demo.domain.repository;

import KupAutoSklep.demo.domain.model.BodyStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyStyleRepository  extends JpaRepository<BodyStyle, Integer> {
    List<BodyStyle> findAll();
}
