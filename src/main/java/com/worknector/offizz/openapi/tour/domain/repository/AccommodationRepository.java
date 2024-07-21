package com.worknector.offizz.openapi.tour.domain.repository;

import com.worknector.offizz.openapi.tour.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
