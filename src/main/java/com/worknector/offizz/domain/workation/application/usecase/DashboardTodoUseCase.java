package com.worknector.offizz.domain.workation.application.usecase;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.work.domain.service.WorkGetService;
import com.worknector.offizz.domain.workation.application.dto.req.VacationTodoFinRequest;
import com.worknector.offizz.domain.workation.application.dto.req.VacationTodoRequest;
import com.worknector.offizz.domain.workation.application.dto.req.WorkTodoFinRequest;
import com.worknector.offizz.domain.workation.application.dto.req.WorkTodoRequest;
import com.worknector.offizz.domain.workation.application.dto.res.*;
import com.worknector.offizz.domain.workation.application.mapper.TodoMapper;
import com.worknector.offizz.domain.workation.domain.entity.*;
import com.worknector.offizz.domain.workation.domain.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

import static com.worknector.offizz.domain.workation.application.mapper.TodoMapper.mapToVacationTodo;
import static com.worknector.offizz.domain.workation.application.mapper.TodoMapper.mapToWorkTodo;

@Transactional
@Service
@RequiredArgsConstructor
public class DashboardTodoUseCase {
    private final DailyGetService dailyGetService;
    private final TodoGetService todoGetService;
    private final TodoSaveService todoSaveService;
    private final TodoUpdateService todoUpdateService;
    private final WorkationGetSave workationGetSave;
    private final WorkGetService workGetService;

    //todo : todo 완료시 근무시간, 조건에 따라 버킷리스트 완료 처리 필요 -> 프론트에 어떤 정보를 응답해야할까 생각

    public WorkHours getCoreTime(User user, int day) {
        Workation workation = workationGetSave.findByUser(user);
        LocalTime startCoreTime = workation.getStartCoreTime();
        LocalTime endCoreTime = workation.getEndCoreTime();
        List<WorkTodo> workTodos = todoGetService.findAllTodos(user, day)
                .workTodos();
        List<TodoHours> todoHours = workTodos.stream()
                .map(TodoMapper::mapToTodoHours)
                .toList();
        return new WorkHours(todoHours, startCoreTime, endCoreTime);
    }

    public void recommendWork(User user) {
        List<WorkKeyword> workKeywords = workationGetSave.findWorkKeyword(user);
        // todo : work, vacation에서 추천항목은 어떤 기준으로 몇개를 보여주는 것인지 확인 필요
    }

    public AllTodoResponse addWorkTodo(User user, int day, WorkTodoRequest request) {
        Daily daily = dailyGetService.findDailyByWorkation(user, day);
        WorkTodo workTodo = mapToWorkTodo(daily, request);
        todoSaveService.saveWorkTodo(workTodo);
        return getAllDailyTodo(user, day);
    }

    public AllTodoResponse addVacationTodo(User user, int day, VacationTodoRequest request) {
        Daily daily = dailyGetService.findDailyByWorkation(user, day);
        VacationTodo vacationTodo = mapToVacationTodo(daily, request);
        todoSaveService.saveVacationTodo(vacationTodo);
        return getAllDailyTodo(user, day);
    }

    public AllTodoResponse finWorkTodo(User user, int day, WorkTodoFinRequest request) {
        todoUpdateService.updateWorkTodoFin(request);
        return getAllDailyTodo(user, day);
    }

    public AllTodoResponse finVacationTodo(User user, int day, VacationTodoFinRequest request) {
        todoUpdateService.updateVacationTodoFin(request);
        return getAllDailyTodo(user, day);
    }

    public AllTodoResponse getAllDailyTodo(User user, int day) {
        AllTodo allTodos = todoGetService.findAllTodos(user, day);
        List<WorkTodoResponse> workTodoResponses = allTodos.workTodos().stream()
                .map(TodoMapper::mapToWorkTodoResponse)
                .toList();
        List<VacationTodoResponse> vacationTodoResponses = allTodos.vacationTodos().stream()
                .map(TodoMapper::mapToVacationTodoResponse)
                .toList();

        return new AllTodoResponse(workTodoResponses, vacationTodoResponses);
    }
}
