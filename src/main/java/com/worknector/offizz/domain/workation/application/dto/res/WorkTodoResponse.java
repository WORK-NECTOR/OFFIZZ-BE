package com.worknector.offizz.domain.workation.application.dto.res;

import java.time.LocalTime;

public record WorkTodoResponse(long workTodoId, int icon, String name, LocalTime time, boolean isComplete) {
}
