package com.worknector.offizz.domain.workation.domain.repository;

import com.worknector.offizz.domain.workation.domain.entity.WorkationVacationKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkationKeywordRepository extends JpaRepository<WorkationVacationKeyword, Long> {
}
