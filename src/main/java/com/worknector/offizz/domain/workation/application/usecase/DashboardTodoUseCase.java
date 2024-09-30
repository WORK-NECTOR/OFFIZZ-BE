package com.worknector.offizz.domain.workation.application.usecase;

import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.service.LikesGetService;
import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.vacation.recommend.domain.service.VacationGetService;
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
import java.util.ArrayList;
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
    private final WorkationGetService workationGetService;
    private final WorkGetService workGetService;
    private final LikesGetService likesGetService;
    private final BucketlistGetService bucketlistGetService;
    private final VacationGetService vacationGetService;

    //todo : todo 완료시 근무시간, 조건에 따라 버킷리스트 완료 처리 필요 -> 프론트에 어떤 정보를 응답해야할까 생각
    public WorkHours getCoreTime(User user, int day) {
        Workation workation = workationGetService.findByUser(user);
        LocalTime startCoreTime = workation.getStartCoreTime();
        LocalTime endCoreTime = workation.getEndCoreTime();
        List<WorkTodo> workTodos = todoGetService.findAllTodos(user, day)
                .workTodos();
        List<TodoHours> todoHours = workTodos.stream()
                .map(TodoMapper::mapToTodoHours)
                .toList();
        return new WorkHours(todoHours, startCoreTime, endCoreTime);
    }

    public RecommendWork recommendWork(User user) {
        Workation workation = workationGetService.findByUser(user);
        List<WorkKeyword> workKeywords = workationGetService.findWorkKeyword(workation);
        List<Likes> likes = likesGetService.findWorkLikes(user);
        List<String> recommendWorks = new ArrayList<>();
        workKeywords.forEach(workKeyword -> recommendWorks.add(workGetService.recommendWork(workKeyword)));
        List<String> likeWorks = workGetService.likeWork(likes);
        return new RecommendWork(recommendWorks, likeWorks);
    }
    // todo : work, vacation에서 추천항목은 어떤 기준으로 몇개를 보여주는 것인지 확인 필요

    public RecommendVacation recommendVacation(User user) {
        Workation workation = workationGetService.findByUser(user);
        List<VacationKeyword> vacationKeywords = workationGetService.findVacationKeyword(workation);
        List<Likes> likes = likesGetService.findVacationLikes(user);
        List<String> recommendVacations = new ArrayList<>();
        vacationKeywords.forEach(vacationKeyword -> recommendVacations.add(vacationGetService.recommendVacation(vacationKeyword)));
        List<String> likeVacations = vacationGetService.likeVacation(likes);
        List<String> bucketlists = bucketlistGetService.byWorkation(workation)
                .stream()
                .map(Bucketlist::getName)
                .toList();
        return new RecommendVacation(recommendVacations, likeVacations, bucketlists);
    }

    public List<RecordVacation> recordVacation(User user, int day) {
        List<VacationTodo> vacationTodos = todoGetService.findAllFinVacationTodo(user, day);
        return vacationTodos.stream()
                .map(TodoMapper::mapToRecordVacation)
                .toList();
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

    public List<RecordVacation> updateVacationTodo(User user, int day, VacationTodoFinRequest request) {
        todoUpdateService.updateVacationTodo(request);
        List<VacationTodo> vacationTodos = todoGetService.findAllFinVacationTodo(user, day);
        return vacationTodos.stream()
                .map(TodoMapper::mapToRecordVacation)
                .toList();
    }

    public List<RecordVacation> deleteRecord(User user, int day, long vacationTodoId) {
        todoUpdateService.updateNotFin(vacationTodoId);
        List<VacationTodo> vacationTodos = todoGetService.findAllFinVacationTodo(user, day);
        return vacationTodos.stream()
                .map(TodoMapper::mapToRecordVacation)
                .toList();
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
