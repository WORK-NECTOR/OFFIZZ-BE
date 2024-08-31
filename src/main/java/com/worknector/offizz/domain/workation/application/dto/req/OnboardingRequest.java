package com.worknector.offizz.domain.workation.application.dto.req;

import com.worknector.offizz.domain.workation.domain.entity.VacationKeyword;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record OnboardingRequest(
        @NotBlank
        @Schema(name = "워케이션에 참여하게 된 주된 이유", example = "이유")
        String reason,
        @NotBlank
        @Schema(name = "어디서 워케이션 참여하는지 (장소명)", example = "씨마크 호텔")
        String locate,
        @NotBlank
        @Schema(name = "어디서 워케이션 참여하는지 (장소 주소)", example = "창해로 307 ...")
        String address,
        @NotNull
        @Schema(name = "워케이션 시작 날짜", example = "2024-08-25")
        LocalDate startDate,
        @NotNull
        @Schema(name = "워케이션 종료 날짜", example = "2024-09-05")
        LocalDate endDate,
        @NotNull
        @Schema(name = "코어타임 시작 시각", example = "09:00")
        LocalTime startCoreTime,
        @NotNull
        @Schema(name = "코어타임 종료 시각", example = "12:00")
        LocalTime endCoreTime,
        @NotNull
        @Schema(name = "여행 키워드 (NATURE / RESTAURANT / CULTURE / SHOPPING)", example = "[NATURE]")
        List<VacationKeyword> vacationKeywords,
        @NotBlank
        @Schema(name = "달성하고 싶은 목표", example = "목표")
        String goal,
        List<Bucketlist> bucketlists
) {
    public record Bucketlist(
            @Schema(name = "방문 예정인 장소명", example = "강릉 OO해변")
            String name,
            @Schema(name = "방문 예정인 장소 위치", example = "창해로 307 ...")
            String address
    ) {}
}
