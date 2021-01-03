package KupAutoSklep.demo.domain.repository;

import KupAutoSklep.demo.domain.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    List<Offer> findAll();


    Offer findById(int offerId);

    List<Offer> findOffersByModel_Manufacturer_Id(int manufacturerId);



}
