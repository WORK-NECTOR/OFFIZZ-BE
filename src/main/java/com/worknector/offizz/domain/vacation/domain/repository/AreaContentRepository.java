package com.worknector.offizz.domain.vacation.domain.repository;

import com.worknector.offizz.domain.vacation.domain.entity.AreaContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaContentRepository extends JpaRepository<AreaContent, Long> {

    Optional<AreaContent> findByContentid(String contentid);
}
