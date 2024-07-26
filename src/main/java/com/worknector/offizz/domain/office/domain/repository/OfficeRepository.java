package com.worknector.offizz.domain.office.domain.repository;

import com.worknector.offizz.domain.office.domain.entity.Office;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, Long> {

  Optional<Office> findByOfficeNameAndStreetAddress(String officeName, String streetAddress);
}
