package com.worknector.offizz.domain.workation.application.dto.req;

import com.worknector.offizz.domain.workation.domain.entity.ConditionType;
import io.swagger.v3.oas.annotations.media.Schema;

public record RetrospectRequest(
        @Schema(description = "work 컨디션 (GREAT/GOOD/OKAY/BAD/HARD)", example = "GREAT")
        ConditionType workCondition,

        @Schema(description = "vacation 컨디션 (GREAT/GOOD/OKAY/BAD/HARD)", example = "GOOD")
        ConditionType vacationCondition,
        @Schema(description = "Y", example = "한 일")
        String dailyY,
        @Schema(description = "W", example = "배운 점")
        String dailyW,
        @Schema(description = "T", example = "다음에 할 일")
        String dailyT
) {
}
