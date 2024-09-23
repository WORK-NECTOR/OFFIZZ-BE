package com.worknector.offizz.domain.workation.application.dto.res;

import java.util.List;

public record AllTodoResponse(List<WorkTodoResponse> workTodoResponses, List<VacationTodoResponse> vacationTodoResponses) {}
