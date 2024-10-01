package com.worknector.offizz.domain.workation.domain.service;

import com.worknector.offizz.domain.workation.application.dto.req.VacationTodoFinRequest;
import com.worknector.offizz.domain.workation.application.dto.req.WorkTodoFinRequest;
import com.worknector.offizz.domain.workation.domain.entity.VacationTodo;
import com.worknector.offizz.domain.workation.domain.entity.WorkTodo;
import com.worknector.offizz.domain.workation.domain.repository.VacationTodoRepository;
import com.worknector.offizz.domain.workation.domain.repository.WorkToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoUpdateService {
    private final WorkToDoRepository workToDoRepository;
    private final VacationTodoRepository vacationTodoRepository;

    public void updateWorkTodoFin(WorkTodoFinRequest request) {
        WorkTodo workTodo = workToDoRepository.findById(request.workTodoId())
                .orElseThrow(IllegalArgumentException::new);
        workTodo.updateFin(request.actualTime(), request.startTime(), request.endTime());
    }

    public void updateVacationTodoFin(VacationTodoFinRequest request) {
        VacationTodo vacationTodo = vacationTodoRepository.findById(request.vacationTodoId())
                .orElseThrow(IllegalArgumentException::new);
        vacationTodo.updateVacationTodoFin(request);
    }

    public void updateVacationTodo(VacationTodoFinRequest request) {
        VacationTodo vacationTodo = vacationTodoRepository.findById(request.vacationTodoId())
                .orElseThrow();
        vacationTodo.updateVacationTodo(request);
    }

    public void updateNotFin(long vacationTodoId) {
        VacationTodo vacationTodo = vacationTodoRepository.findById(vacationTodoId)
                .orElseThrow();
        vacationTodo.updateNotFin();
    }
}
