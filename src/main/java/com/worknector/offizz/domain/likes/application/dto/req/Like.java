package com.worknector.offizz.domain.likes.application.dto.req;

import com.worknector.offizz.domain.likes.domain.entity.LikesCategory;

public record Like(
        LikesCategory category,
        long id
) {
}
