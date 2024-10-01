package com.worknector.offizz.domain.workation.application.dto.req;

import java.time.LocalTime;

public record WorkTodoFinRequest(long workTodoId, LocalTime actualTime, LocalTime startTime, LocalTime endTime) {}
