package com.worknector.offizz.domain.workation.domain.repository;

import com.worknector.offizz.domain.workation.domain.entity.Workation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkationRepository extends JpaRepository<Workation, Long> {
}
