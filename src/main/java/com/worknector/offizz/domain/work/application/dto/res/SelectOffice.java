package com.worknector.offizz.domain.work.application.dto.res;

import com.worknector.offizz.domain.work.domain.entity.Office;

public record SelectOffice(
        Office office,
        boolean isLike
) {
}
