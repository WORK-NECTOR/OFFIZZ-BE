package com.worknector.offizz.openapi.tour.domain.repository;

import com.worknector.offizz.openapi.tour.domain.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    Optional<Accommodation> findByContentid(String contentid);
}
