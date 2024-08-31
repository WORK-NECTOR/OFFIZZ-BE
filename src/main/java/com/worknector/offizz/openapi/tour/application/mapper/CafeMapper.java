package com.worknector.offizz.openapi.tour.application.mapper;

import com.worknector.offizz.domain.work.domain.entity.Cafe;
import com.worknector.offizz.openapi.tour.application.dto.CafeResponse.Item;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CafeMapper {
    public static Cafe responseToCafe(Item cafeResponse) {
        return Cafe.builder()
                .cafeName(cafeResponse.title())
                .streetAddress(cafeResponse.addr1())
                .contentId(cafeResponse.contentid())
                .image(cafeResponse.firstimage())
                .tel(cafeResponse.tel())
                .lon(Double.parseDouble(cafeResponse.mapx()))
                .lat(Double.parseDouble(cafeResponse.mapy()))
                .build();
    }
}
