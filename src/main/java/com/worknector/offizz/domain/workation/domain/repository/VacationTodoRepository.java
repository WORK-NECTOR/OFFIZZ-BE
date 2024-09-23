package com.worknector.offizz.domain.workation.domain.repository;

import com.worknector.offizz.domain.workation.domain.entity.Daily;
import com.worknector.offizz.domain.workation.domain.entity.VacationTodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationTodoRepository extends JpaRepository<VacationTodo, Long> {
    List<VacationTodo> findAllByDaily(Daily daily);
}
