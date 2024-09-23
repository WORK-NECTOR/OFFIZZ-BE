package com.worknector.offizz.domain.workation.domain.repository;

import com.worknector.offizz.domain.workation.domain.entity.Daily;
import com.worknector.offizz.domain.workation.domain.entity.WorkTodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkToDoRepository extends JpaRepository<WorkTodo, Long> {
    List<WorkTodo> findAllByDaily(Daily daily);
}
