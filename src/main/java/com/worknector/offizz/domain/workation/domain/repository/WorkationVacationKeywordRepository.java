package com.worknector.offizz.domain.workation.domain.repository;

import com.worknector.offizz.domain.workation.domain.entity.Workation;
import com.worknector.offizz.domain.workation.domain.entity.WorkationVacationKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkationVacationKeywordRepository extends JpaRepository<WorkationVacationKeyword, Long> {
    List<WorkationVacationKeyword> findAllByWorkation(Workation workation);
}
