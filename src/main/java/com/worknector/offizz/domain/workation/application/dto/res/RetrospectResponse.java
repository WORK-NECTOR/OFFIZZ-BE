package com.worknector.offizz.domain.workation.application.dto.res;

import com.worknector.offizz.domain.workation.domain.entity.ConditionType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record RetrospectResponse(
        @Schema(description = "총 업무 시간 - 시간", example = "2")
        int actualWorkHours,
        @Schema(description = "총 업무 시간 - 분", example = "30")
        int actualWorkMinutes,
        @Schema(description = "완료한 여행", example = "3")
        int completedVacation,
        @Schema(description = "여행 기록 리스트")
        List<RecordVacation> vacationRecords,
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
