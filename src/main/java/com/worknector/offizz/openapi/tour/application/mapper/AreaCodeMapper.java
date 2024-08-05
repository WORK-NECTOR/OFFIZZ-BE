package com.worknector.offizz.openapi.tour.application.mapper;

import com.worknector.offizz.domain.vacation.domain.entity.AreaCode;
import com.worknector.offizz.openapi.tour.application.dto.AreaCodeResponse;

public class AreaCodeMapper {

    private AreaCodeMapper() {
        throw new IllegalArgumentException();
    }

    public static AreaCode toEntity(AreaCodeResponse.Item item) {
        return AreaCode.builder()
                .code(item.code())
                .name(item.name())
                .build();
    }
}
