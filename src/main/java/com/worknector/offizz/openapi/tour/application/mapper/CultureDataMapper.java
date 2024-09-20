package com.worknector.offizz.openapi.tour.application.mapper;

import com.worknector.offizz.domain.vacation.culture.domain.entity.Culture;
import com.worknector.offizz.openapi.tour.application.dto.TourOpenDataResponse;

public class CultureDataMapper {

    private CultureDataMapper() {
        throw new IllegalArgumentException();
    }

    public static Culture toEntity(TourOpenDataResponse.Item item) {
        return Culture.builder()
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
