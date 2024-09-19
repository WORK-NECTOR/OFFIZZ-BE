package com.worknector.offizz.domain.workation.domain.repository;

import com.worknector.offizz.domain.workation.domain.entity.WorkKeyword;
import com.worknector.offizz.domain.workation.domain.entity.Workation;
import com.worknector.offizz.domain.workation.domain.entity.WorkationWorkKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkationWorkKeywordRepository extends JpaRepository<WorkationWorkKeyword, Long> {
    List<WorkKeyword> findAllByWorkation(Workation workation);
}
