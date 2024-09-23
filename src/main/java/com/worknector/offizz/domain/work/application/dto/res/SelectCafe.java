package com.worknector.offizz.domain.work.application.dto.res;

import com.worknector.offizz.domain.work.domain.entity.Cafe;

public record SelectCafe(
        Cafe cafe,
        boolean isLike
) {
}
