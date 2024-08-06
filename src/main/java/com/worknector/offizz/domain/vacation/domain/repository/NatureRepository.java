package com.worknector.offizz.domain.vacation.domain.repository;

import com.worknector.offizz.domain.vacation.domain.entity.Nature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NatureRepository extends JpaRepository<Nature, Long> {

    Optional<Nature> findByContentid(String contentid);
}
