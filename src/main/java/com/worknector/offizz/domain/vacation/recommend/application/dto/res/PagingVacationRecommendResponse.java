package com.worknector.offizz.domain.vacation.recommend.application.dto.res;

import java.util.List;

public record PagingVacationRecommendResponse(
        List<VacationRecommendResponse> vacationRecommendResponses,
        int totalPage
) {
}
