package com.worknector.offizz.domain.workation.application.dto.res;

import java.util.List;

public record RecommendVacation(
        List<String> recommends,
        List<String> likes,
        List<String> buceklists
) {
}
