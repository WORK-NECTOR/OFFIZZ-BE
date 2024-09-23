package com.worknector.offizz.domain.workation.application.dto.res;

import com.worknector.offizz.domain.workation.domain.entity.VacationTodo;
import com.worknector.offizz.domain.workation.domain.entity.WorkTodo;

import java.util.List;

public record AllTodo (List<WorkTodo> workTodos, List<VacationTodo> vacationTodos){}
