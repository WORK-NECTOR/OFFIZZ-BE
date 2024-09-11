package com.worknector.offizz.domain.vacation.nature.application.dto.res;

import java.util.List;

public record RecommendNaturePagingResponse(
        List<RecommendNature> recommendNatures,
        int totalPage
) {
}
