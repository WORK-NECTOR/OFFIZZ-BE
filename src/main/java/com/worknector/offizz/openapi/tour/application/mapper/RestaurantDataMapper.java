package com.worknector.offizz.openapi.tour.application.mapper;

import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;
import com.worknector.offizz.openapi.tour.application.dto.AreaBasedNatureResponse;

public class RestaurantDataMapper {

    private RestaurantDataMapper() {
        throw new IllegalArgumentException();
    }

    public static Restaurant toEntity(AreaBasedNatureResponse.Item item) {
        return Restaurant.builder()
                .addr1(item.addr1())
                .lon(Double.parseDouble(item.mapx()))
                .lat(Double.parseDouble(item.mapy()))
                .modifiedtime(item.modifiedtime())
                .tel(item.tel())
                .title(item.title())
                .contentid(item.contentid())
                .contenttypeid(item.contenttypeid())
                .createdtime(item.createdtime())
                .firstimage(item.firstimage())
                .firstimage2(item.firstimage2())
                .areacode(item.areacode())
                .sigungucode(item.sigungucode())
                .cat1(item.cat1())
                .cat2(item.cat2())
                .cat3(item.cat3())
                .build();
    }
}
