package com.worknector.offizz.domain.workation.domain.service;

import com.worknector.offizz.domain.workation.domain.entity.VacationTodo;
import com.worknector.offizz.domain.workation.domain.entity.WorkTodo;
import com.worknector.offizz.domain.workation.domain.repository.DailyRepository;
import com.worknector.offizz.domain.workation.domain.repository.VacationTodoRepository;
import com.worknector.offizz.domain.workation.domain.repository.WorkToDoRepository;
import com.worknector.offizz.domain.workation.domain.repository.WorkationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoSaveService {
    private final WorkToDoRepository workToDoRepository;
    private final VacationTodoRepository vacationTodoRepository;
    private final WorkationRepository workationRepository;
    private final DailyRepository dailyRepository;

    public void saveWorkTodo(WorkTodo workTodo) {

        workToDoRepository.save(workTodo);
    }

    public void saveVacationTodo(VacationTodo vacationTodo) {
        vacationTodoRepository.save(vacationTodo);
    }
}
