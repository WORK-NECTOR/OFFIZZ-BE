package com.worknector.offizz.domain.workation.presentation;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.application.dto.req.VacationTodoFinRequest;
import com.worknector.offizz.domain.workation.application.dto.req.VacationTodoRequest;
import com.worknector.offizz.domain.workation.application.dto.req.WorkTodoFinRequest;
import com.worknector.offizz.domain.workation.application.dto.req.WorkTodoRequest;
import com.worknector.offizz.domain.workation.application.dto.res.*;
import com.worknector.offizz.domain.workation.application.usecase.DashboardTodoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
@Tag(name = "DASHBOARD Controller")
public class DashBoardController {
    private final DashboardTodoUseCase dashboardTodoUseCase;

    @GetMapping("/record/vacation/{day}")
    @Operation(summary = "여행 기록 조회", description = "토큰과 며칠째 워케이션인지 PathVariable로 요청")
    public ResponseEntity recordVacation(@AuthenticationPrincipal User user, @PathVariable int day) {
        List<RecordVacation> recordVacations = dashboardTodoUseCase.recordVacation(user, day);
        return ResponseEntity.ok(recordVacations);
    }

    @GetMapping("/recommend/vacation")
    @Operation(summary = "Vacation의 버킷리스트 겸 추천 부분", description = "토큰 필요")
    public ResponseEntity<RecommendVacation> vacationBucketList(@AuthenticationPrincipal User user) {
        RecommendVacation recommendVacation = dashboardTodoUseCase.recommendVacation(user);
        return ResponseEntity.ok(recommendVacation);
    }

    @GetMapping("/recommend/work")
    @Operation(summary = "Work의 추천 부분", description = "토큰 필요")
    public ResponseEntity<RecommendWork> workBucketList(@AuthenticationPrincipal User user) {
        RecommendWork recommendWork = dashboardTodoUseCase.recommendWork(user);
        return ResponseEntity.ok(recommendWork);
    }

    @GetMapping("/coretime/{day}")
    @Operation(summary = "오늘의 코어타임과 작업 시간 확인", description = "토큰과 워케이션 날짜 포함")
    public ResponseEntity<WorkHours> coreTime(@AuthenticationPrincipal User user, @PathVariable int day) {
        WorkHours coreTime = dashboardTodoUseCase.getCoreTime(user, day);
        return ResponseEntity.ok(coreTime);
    }


    @GetMapping("/todo/{day}")
    @Operation(summary = "오늘의 투두를 조회한다 (work, vacation 모두)", description = "토큰과 URL에 워케이션 날짜(1일차, 2일차 등등)을 포함해야 함")
    public ResponseEntity<AllTodoResponse> allTodo(@AuthenticationPrincipal User user, @PathVariable int day) {
        AllTodoResponse allTodoResponse = dashboardTodoUseCase.getAllDailyTodo(user, day);
        return ResponseEntity.ok(allTodoResponse);
    }


    @PostMapping("/work/todo/{day}")
    @Operation(summary = "work 투두 추가", description = "토큰과 URL에 워케이션 날짜(1일차, 2일차 등등)을 포함하고, body에 투두 정보 기입")
    public ResponseEntity<AllTodoResponse> addTodo(@AuthenticationPrincipal User user, @PathVariable int day, @RequestBody WorkTodoRequest request) {
        AllTodoResponse allTodoResponse = dashboardTodoUseCase.addWorkTodo(user, day, request);
        return ResponseEntity.ok(allTodoResponse);
    }

    @PostMapping("/vacation/todo/{day}")
    @Operation(summary = "vacation 투두 추가", description = "토큰과 URL에 워케이션 날짜(1일차, 2일차 등등)을 포함하고, body에 투두 정보 기입")
    public ResponseEntity<AllTodoResponse> addTodo(@AuthenticationPrincipal User user, @PathVariable int day, @RequestBody VacationTodoRequest request) {
        AllTodoResponse allTodoResponse = dashboardTodoUseCase.addVacationTodo(user, day, request);
        return ResponseEntity.ok(allTodoResponse);
    }

    @PatchMapping("/work/todo/fin/{day}")
    @Operation(summary = "work 투두 완료", description = "토큰과 URL에 워케이션 날짜(1일차, 2일차 등등)을 포함하고, body에 투두 정보 기입")
    public ResponseEntity<AllTodoResponse> finTodo(@AuthenticationPrincipal User user, @PathVariable int day, @RequestBody WorkTodoFinRequest request) {
        AllTodoResponse allTodoResponse = dashboardTodoUseCase.finWorkTodo(user, day, request);
        return ResponseEntity.ok(allTodoResponse);
    }

    @PatchMapping("/vacation/todo/fin/{day}")
    @Operation(summary = "vacation 투두 완료", description = "토큰과 URL에 워케이션 날짜(1일차, 2일차 등등)을 포함하고, body에 투두 정보 기입")
    public ResponseEntity<AllTodoResponse> finTodo(@AuthenticationPrincipal User user, @PathVariable int day, @RequestBody VacationTodoFinRequest request) {
        AllTodoResponse allTodoResponse = dashboardTodoUseCase.finVacationTodo(user, day, request);
        return ResponseEntity.ok(allTodoResponse);
    } //todo : workTodo와 완료 로직 다름 -> 수정 필요
}
