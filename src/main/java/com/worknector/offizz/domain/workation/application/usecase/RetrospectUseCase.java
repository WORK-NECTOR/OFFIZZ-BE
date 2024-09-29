package com.worknector.offizz.domain.workation.application.usecase;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.application.dto.req.RetrospectRequest;
import com.worknector.offizz.domain.workation.application.dto.res.RecordVacation;
import com.worknector.offizz.domain.workation.application.dto.res.RetrospectResponse;
import com.worknector.offizz.domain.workation.application.mapper.TodoMapper;
import com.worknector.offizz.domain.workation.domain.entity.Daily;
import com.worknector.offizz.domain.workation.domain.entity.VacationTodo;
import com.worknector.offizz.domain.workation.domain.entity.WorkTodo;
import com.worknector.offizz.domain.workation.domain.service.DailyGetService;
import com.worknector.offizz.domain.workation.domain.service.DailyUpdateService;
import com.worknector.offizz.domain.workation.domain.service.TodoGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static com.worknector.offizz.domain.workation.application.mapper.RetrospectMapper.mapToRetrospectResponse;

@RequiredArgsConstructor
@Transactional
@Service
public class RetrospectUseCase {

    private final DailyGetService dailyGetService;
    private final TodoGetService todoGetService;
    private final DailyUpdateService dailyUpdateService;

    public RetrospectResponse getRetrospectByDay(User user, int day) {
        Daily daily = dailyGetService.findDailyByWorkation(user, day);
        List<VacationTodo> vacationTodos = todoGetService.findAllFinVacationTodo(user, day);
        List<RecordVacation> vacationRecords = getRecordVacations(vacationTodos);
        Duration totalWorkTime = calculateTotalWorkTime(daily);

        return mapToRetrospectResponse(
                totalWorkTime.toHoursPart(),
                totalWorkTime.toMinutesPart(),
                vacationTodos.size(),
                vacationRecords,
                daily
        );
    }

    public Long updateRetrospect(User user, int day, RetrospectRequest retrospectRequest) {
        Daily daily = dailyGetService.findDailyByWorkation(user, day);
        dailyUpdateService.updateDailyRetrospect(daily, retrospectRequest);

        return daily.getDailyId();
    }

    /**
     * 여행 기록 조회
     * @param vacationTodos 완료한 여행 리스트
     * @return 여행 기록 DTO 반환
     */
    private List<RecordVacation> getRecordVacations(List<VacationTodo> vacationTodos) {
        return vacationTodos.stream()
                .map(TodoMapper::mapToRecordVacation)
                .toList();
    }

    /**
     * 총 업무 시간 계산 (일자별)
     * LocalTime을 Duration으로 변환해서 계신 후, Duration 합산을 반환
     * @param daily Workation 일자
     * @return 총 Duration
     */
    private Duration calculateTotalWorkTime(Daily daily) {
        List<WorkTodo> workTodos = todoGetService.findAllWorkTodosByDaily(daily);

        return workTodos.stream()
                .map(WorkTodo::getActualTime)
                .map(time -> Duration.between(LocalTime.MIN, time))
                .reduce(Duration.ZERO, Duration::plus);
    }
}
