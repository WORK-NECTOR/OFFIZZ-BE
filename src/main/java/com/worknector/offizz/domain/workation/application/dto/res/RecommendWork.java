package com.worknector.offizz.domain.workation.application.dto.res;

import java.util.List;

public record RecommendWork(
        List<String> recommends,
        List<String> likes
) {
}
