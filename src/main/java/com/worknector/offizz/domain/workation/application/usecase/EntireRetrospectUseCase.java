package com.worknector.offizz.domain.workation.application.usecase;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.application.dto.req.EntireRetrospectRequest;
import com.worknector.offizz.domain.workation.application.dto.res.AllRecapResponse;
import com.worknector.offizz.domain.workation.application.dto.res.AllTodo;
import com.worknector.offizz.domain.workation.application.dto.res.RecapResponse;
import com.worknector.offizz.domain.workation.application.mapper.WorkationMapper;
import com.worknector.offizz.domain.workation.domain.entity.*;
import com.worknector.offizz.domain.workation.domain.service.DailyGetService;
import com.worknector.offizz.domain.workation.domain.service.TodoGetService;
import com.worknector.offizz.domain.workation.domain.service.WorkationGetService;
import com.worknector.offizz.domain.workation.domain.service.WorkationUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Service
@Transactional
public class EntireRetrospectUseCase {
    private final WorkationUpdateService workationUpdateService;
    private final TodoGetService todoGetService;
    private final DailyGetService dailyGetService;
    private final WorkationGetService workationGetService;

    public RecapResponse saveRetrospect(User user, EntireRetrospectRequest request) {
        Workation workation = workationUpdateService.updateWorkationFin(user, request);
        return getEntireRetrospect(workation);
    }

    public List<AllRecapResponse> findAllRecap(User user) {
        List<Workation> workations = workationGetService.allWorkation(user);
        return workations.stream()
                .filter(workation -> workation.getRetrospect() != null)
                .map(WorkationMapper::mapToRecapResponse)
                .toList();
    }

    public RecapResponse getRecap(long workationId) {
        Workation workation = workationGetService.findByWorkationId(workationId);
        return getEntireRetrospect(workation);
    }

    private RecapResponse getEntireRetrospect(Workation workation) {
        RecapResponse.ZeroPage zeroPage = new RecapResponse.ZeroPage(workation.getLocate(), workation.getAddress(), workation.getStartDate(), workation.getEndDate());
        String reason = workation.getReason();
        RecapResponse.FirstPage firstPage = new RecapResponse.FirstPage(reason);

        AllTodo allTodo = todoGetService.findAllTodoEveryday(workation);
        List<WorkTodo> workTodos = allTodo.workTodos();
        long totalMinute = workTodos.stream()
                .mapToLong(workTodo -> Duration.between(LocalTime.MIN, workTodo.getActualTime()).toMinutes())
                .sum();
        // 평균 시작시간, 끝 시간 구하기 애매해서, 우선 더미로 사용하기 위해 코어 타임으로 진행
        LocalTime startCoreTime = workation.getStartCoreTime();
        LocalTime endCoreTime = workation.getEndCoreTime();
        long averageMinute = Duration.between(startCoreTime, endCoreTime).toMinutes();

        RecapResponse.SecondPage secondPage = new RecapResponse.SecondPage(totalMinute, startCoreTime, endCoreTime, averageMinute);
        // 업무 만족도는 표시하지 않음 (없음)
        // 여기까지 페이지 1

        long finishCount = workTodos.stream()
                .filter(WorkTodo::isComplete)
                .count();

        double finishRate = workTodos.size() == 0 ? 100 : (finishCount / workTodos.size()) * 100;
        RecapResponse.ThirdPage thirdPage = new RecapResponse.ThirdPage(finishRate);
        // 페이지 2

        List<Daily> dailies = dailyGetService.findAllDailyByWorkation(workation);
        RecapResponse.FourthPage fourthPage = getFourthPage(workation, dailies);
        //페이지 4

        List<VacationTodo> vacationTodos = allTodo.vacationTodos();
        List<VacationTodo> finVacationTodos = vacationTodos.stream()
                .filter(VacationTodo::isComplete)
                .collect(toCollection(ArrayList::new));
        RecapResponse.FifthPage fifthPage = getFifthPage(finVacationTodos);
        //페이지 5

        RecapResponse.SixthPage sixthPage = getSixthPage(vacationTodos);

        RecapResponse.SeventhPage seventhPage = getSevenPage(finVacationTodos);
//        페이지 9

        return new RecapResponse(zeroPage, firstPage, secondPage, thirdPage, fourthPage, fifthPage, sixthPage, seventhPage);
    }

    private RecapResponse.FourthPage getFourthPage(Workation workation, List<Daily> dailies) {
        int day = 0;
        long total = 0;
        long diff = 0;
        for (int i = 0; i < dailies.size(); i++) {
            Daily daily = dailies.get(i);
            long totalTime = todoGetService.findAllWorkTodos(daily)
                    .stream()
                    .mapToLong(workTodo -> Duration.between(LocalTime.MIN, workTodo.getActualTime()).toMinutes())
                    .sum();

            if (total < totalTime) {
                diff = totalTime - total;
                total = totalTime;
                day = daily.getDay();
            }
//            if (bestScore < daily.getWorkCondition()) {
//                bestScore = daily.getWorkCondition();
//            }
        }

        LocalDate bestWorkDay = workation.getStartDate().plusDays(day - 1);
        return new RecapResponse.FourthPage(bestWorkDay, diff);
    }

    private RecapResponse.FifthPage getFifthPage(List<VacationTodo> finVacationTodos) {
        int finishVacationCount = finVacationTodos.size();
        double finishVacationRate = finVacationTodos.stream()
                .mapToDouble(VacationTodo::getRating)
                .sum()/finishVacationCount;
        return new RecapResponse.FifthPage(finishVacationCount,finishVacationRate);
    }

    private RecapResponse.SixthPage getSixthPage(List<VacationTodo> vacationTodos) {
        long count = vacationTodos.stream()
                .filter(VacationTodo::isComplete)
                .count();
        double finishRate = vacationTodos.size() == 0 ? 100 : (count / vacationTodos.size()) * 100;

        return new RecapResponse.SixthPage(finishRate);
    }

    private RecapResponse.SeventhPage getSevenPage(List<VacationTodo> finVacationTodos) {
        finVacationTodos.sort(new Comparator<VacationTodo>() {
            @Override
            public int compare(VacationTodo o1, VacationTodo o2) {
                if (o1.getRating() <= o2.getRating())
                    return 1;
                return -1;
            }
        });
        int last = 0;
        for (int i = 0; i < finVacationTodos.size(); i++) {
            VacationTodo vacationTodo = finVacationTodos.get(i);
            VacationTodo bestVacation = finVacationTodos.get(last);
            Double rating = vacationTodo.getRating();
            if (rating < bestVacation.getRating())
                break;
            last = i;
        }

        // last 까지 모두 만족도 최대 추가
        List<RecapResponse.BestVacation> bestVacations = new ArrayList<>();
        for (int i = 0; i <= last; i++) {
            VacationTodo vacationTodo = finVacationTodos.get(i);
            RecapResponse.BestVacation bestVacation = new RecapResponse.BestVacation(vacationTodo.getDaily().getDate(), vacationTodo.getName());
            bestVacations.add(bestVacation);
        }
        return new RecapResponse.SeventhPage(bestVacations);
    }
}
