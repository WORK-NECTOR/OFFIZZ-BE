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

import java.util.ArrayList;
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

    public List<WorkTodo> findAllWorkTodos(Daily daily) {
        return workToDoRepository.findAllByDaily(daily);
    }

    public List<VacationTodo> findAllVacationTodo(Daily daily) {
        return vacationTodoRepository.findAllByDaily(daily);
    }

    public AllTodo findAllTodoEveryday(Workation workation) {
        List<Daily> dailies = dailyRepository.findAllByWorkation(workation);
        List<WorkTodo> workTodos = new ArrayList<>();
        List<VacationTodo> vacationTodos = new ArrayList<>();
        dailies.forEach(daily -> {
            workToDoRepository.findAllByDaily(daily).forEach(workTodos::add);
            vacationTodoRepository.findAllByDaily(daily).forEach(vacationTodos::add);
        });
        return new AllTodo(workTodos, vacationTodos);
    }

    public List<VacationTodo> findAllFinVacationTodo(User user, int day) {
        Workation workation = workationRepository.findAllByUserOrderByCreatedAtDesc(user)
                .stream()
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        Daily daily = dailyRepository.findByWorkationAndDay(workation, day)
                .orElseThrow(IllegalArgumentException::new);
        return vacationTodoRepository.findAllByIsCompleteIsTrueAndDaily(daily);
    }

    public List<WorkTodo> findAllWorkTodosByDaily(Daily daily) {
        return workToDoRepository.findAllByDaily(daily);
    }
}
