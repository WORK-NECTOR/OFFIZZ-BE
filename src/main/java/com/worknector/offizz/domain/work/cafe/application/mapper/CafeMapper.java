package com.worknector.offizz.domain.work.cafe.application.mapper;

import com.worknector.offizz.domain.work.cafe.application.dto.res.CafeWithLatAndLon;
import com.worknector.offizz.domain.work.cafe.domain.entity.Cafe;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CafeMapper {
    public static CafeWithLatAndLon mapToCafeWithLatAndLon(Cafe cafe) {
        return new CafeWithLatAndLon(
                cafe.getCafeId(),
                cafe.getCafeName(),
                cafe.getStreetAddress(),
                cafe.getLat(),
                cafe.getLon(),
                cafe.getImage()
        );
    }
}
