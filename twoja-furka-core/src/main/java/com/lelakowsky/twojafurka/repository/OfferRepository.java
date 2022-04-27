package com.lelakowsky.twojafurka.repository;

import com.lelakowsky.twojafurka.domain.offer.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
