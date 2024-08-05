package com.worknector.offizz.domain.vacation.domain.repository;

import com.worknector.offizz.domain.vacation.domain.entity.AreaCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaCodeRepository extends JpaRepository<AreaCode, Long> {

    Optional<AreaCode> findByName(String name);
}
