package com.worknector.offizz.domain.workation.application.dto.res;

public record RecordVacation(
        long vacationTodoId,
        String title,
        String locate,
        String comment,
        String image
) {
}
