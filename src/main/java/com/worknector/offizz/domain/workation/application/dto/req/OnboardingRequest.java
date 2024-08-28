package com.worknector.offizz.domain.workation.application.dto.req;

import com.worknector.offizz.domain.workation.domain.entity.Keyword;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record OnboardingRequest(
        String reason,
        String locate,
        String address,
        LocalDate startDate,
        LocalDate endDate,
        LocalTime startCoreTime,
        LocalTime endCoreTime,
        List<Keyword> keywords,
        String goal,
        List<Bucketlist> bucketlists
) {
    public record Bucketlist(
            String name,
            String address
    ) {}
}
