package com.worknector.offizz.domain.workation.application.mapper;

import com.worknector.offizz.domain.workation.application.dto.req.VacationTodoRequest;
import com.worknector.offizz.domain.workation.application.dto.req.WorkTodoRequest;
import com.worknector.offizz.domain.workation.application.dto.res.RecordVacation;
import com.worknector.offizz.domain.workation.application.dto.res.TodoHours;
import com.worknector.offizz.domain.workation.application.dto.res.VacationTodoResponse;
import com.worknector.offizz.domain.workation.application.dto.res.WorkTodoResponse;
import com.worknector.offizz.domain.workation.domain.entity.Daily;
import com.worknector.offizz.domain.workation.domain.entity.VacationTodo;
import com.worknector.offizz.domain.workation.domain.entity.WorkTodo;

public class TodoMapper {
    public static WorkTodoResponse mapToWorkTodoResponse(WorkTodo workTodo) {
        if (workTodo.isComplete()) {
            return new WorkTodoResponse(
                    workTodo.getWorkTodoId(),
                    workTodo.getIcon(),
                    workTodo.getName(),
                    workTodo.getActualTime(),
                    true
            );
        }
        return new WorkTodoResponse(
                workTodo.getWorkTodoId(),
                workTodo.getIcon(),
                workTodo.getName(),
                workTodo.getPlanTime(),
                false
        );
    }

    public static VacationTodoResponse mapToVacationTodoResponse(VacationTodo vacationTodo) {
        return new VacationTodoResponse(vacationTodo.getVacationTodoId(), vacationTodo.getIcon(), vacationTodo.getName(), vacationTodo.isComplete());
    }

    public static WorkTodo mapToWorkTodo(Daily daily, WorkTodoRequest request) {
        return WorkTodo.builder()
                .name(request.name())
                .icon(request.icon())
                .planTime(request.planTime())
                .daily(daily)
                .build();
    }

    public static VacationTodo mapToVacationTodo(Daily daily, VacationTodoRequest request) {
        return VacationTodo.builder()
                .icon(request.icon())
                .name(request.name())
                .daily(daily)
                .build();
    }

    public static TodoHours mapToTodoHours(WorkTodo workTodo) {
        return new TodoHours(workTodo.getName(), workTodo.getStartTime(), workTodo.getEndTime());
    }

    public static RecordVacation mapToRecordVacation(VacationTodo vacationTodo) {
        return new RecordVacation(vacationTodo.getVacationTodoId(), vacationTodo.getName(), vacationTodo.getLocate(), vacationTodo.getComment(), vacationTodo.getImage());
    }
}
