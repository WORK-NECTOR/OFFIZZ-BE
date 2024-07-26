package com.worknector.offizz.openapi.tour.application.mapper;

import com.worknector.offizz.openapi.tour.application.dto.AccommodationResponse;
import com.worknector.offizz.domain.accommodation.domain.entity.Accommodation;

public class AccommodationMapper {

    private AccommodationMapper() {
        throw new IllegalArgumentException();
    }

    public static Accommodation toEntity(AccommodationResponse.Item item) {
        return Accommodation.builder()
                .addr1(item.addr1())
                .addr2(item.addr2())
                .mapx(item.mapx())
                .mapy(item.mapy())
                .mlevel(item.mlevel())
                .modifiedtime(item.modifiedtime())
                .tel(item.tel())
                .title(item.title())
                .contentid(item.contentid())
                .firstimage(item.firstimage())
                .firstimage2(item.firstimage2())
                .build();
    }
}
