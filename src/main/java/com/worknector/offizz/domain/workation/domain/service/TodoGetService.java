package com.worknector.offizz.domain.workation.domain.service;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.application.dto.res.AllTodo;
import com.worknector.offizz.domain.workation.domain.entity.Daily;
import com.worknector.offizz.domain.workation.domain.entity.VacationTodo;
import com.worknector.offizz.domain.workation.domain.entity.WorkTodo;
import com.worknector.offizz.domain.workation.domain.entity.Workation;
import com.worknector.offizz.domain.workation.domain.repository.DailyRepository;
import com.worknector.offizz.domain.workation.domain.repository.VacationTodoRepository;
import com.worknector.offizz.domain.workation.domain.repository.WorkToDoRepository;
import com.worknector.offizz.domain.workation.domain.repository.WorkationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoGetService {
    private final WorkationRepository workationRepository;
    private final DailyRepository dailyRepository;
    private final WorkToDoRepository workToDoRepository;
    private final VacationTodoRepository vacationTodoRepository;

    public AllTodo findAllTodos(User user, int day) {
        Workation workation = workationRepository.findAllByUserOrderByCreatedAtDesc(user)
                .stream()
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        Daily daily = dailyRepository.findByWorkationAndDay(workation, day)
                .orElseThrow(IllegalArgumentException::new);
        List<WorkTodo> workTodos = workToDoRepository.findAllByDaily(daily);
        List<VacationTodo> vacationTodos = vacationTodoRepository.findAllByDaily(daily);
        return new AllTodo(workTodos, vacationTodos);
    }
}
