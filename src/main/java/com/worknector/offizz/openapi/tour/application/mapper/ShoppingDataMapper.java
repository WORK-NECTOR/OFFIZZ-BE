package com.worknector.offizz.openapi.tour.application.mapper;

import com.worknector.offizz.domain.vacation.shopping.domain.entity.Shopping;
import com.worknector.offizz.openapi.tour.application.dto.TourOpenDataResponse;

public class ShoppingDataMapper {

    private ShoppingDataMapper() {
        throw new IllegalArgumentException();
    }

    public static Shopping toEntity(TourOpenDataResponse.Item item) {
        return Shopping.builder()
                .addr1(item.addr1())
                .lon(Double.parseDouble(item.mapx()))
                .lat(Double.parseDouble(item.mapy()))
                .modifiedtime(item.modifiedtime())
                .title(item.title())
                .contentid(item.contentid())
                .contenttypeid(item.contenttypeid())
                .createdtime(item.createdtime())
                .firstimage(item.firstimage())
                .areacode(item.areacode())
                .sigungucode(item.sigungucode())
                .build();
    }
}
