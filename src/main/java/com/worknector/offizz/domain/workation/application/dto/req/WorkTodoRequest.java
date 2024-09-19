package com.worknector.offizz.domain.workation.application.dto.req;

import java.time.LocalTime;

public record WorkTodoRequest(int icon, LocalTime planTime, String name) {}
