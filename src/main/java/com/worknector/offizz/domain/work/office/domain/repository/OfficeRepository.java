package com.worknector.offizz.domain.work.office.domain.repository;

import com.worknector.offizz.domain.work.office.domain.entity.Office;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, Long>, OfficeDslRepository {

  Optional<Office> findByOfficeNameAndStreetAddress(String officeName, String streetAddress);
}
