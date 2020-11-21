package KupAutoSklep.demo;

import KupAutoSklep.demo.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    List<Offer> findAll();

    Page<Offer> findAllByYearBetweenAndModel_ManufacturerId(@Min(1900) Integer year,@Min(1900) Integer year2, Integer model_manufacturer_id, Pageable pageable);

    Offer findById(int offerId);

    List<Offer> findOffersByModel_Manufacturer_Id(int manufacturerId);



}
