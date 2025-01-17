package com.worknector.offizz.domain.vacation.nature.domain.repository;

import com.worknector.offizz.domain.vacation.nature.domain.entity.Nature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NatureRepository extends JpaRepository<Nature, Long>, NatureDslRepository {
    Optional<Nature> findByContentid(String contentid);
}
