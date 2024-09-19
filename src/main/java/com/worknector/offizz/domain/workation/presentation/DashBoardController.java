package com.worknector.offizz.domain.workation.presentation;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.application.dto.req.VacationTodoFinRequest;
import com.worknector.offizz.domain.workation.application.dto.req.VacationTodoRequest;
import com.worknector.offizz.domain.workation.application.dto.req.WorkTodoFinRequest;
import com.worknector.offizz.domain.workation.application.dto.req.WorkTodoRequest;
import com.worknector.offizz.domain.workation.application.dto.res.AllTodoResponse;
import com.worknector.offizz.domain.workation.application.dto.res.WorkHours;
import com.worknector.offizz.domain.workation.application.usecase.DashboardTodoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
@Tag(name = "DASHBOARD Controller")
public class DashBoardController {
    private final DashboardTodoUseCase dashboardTodoUseCase;

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
    }
}
