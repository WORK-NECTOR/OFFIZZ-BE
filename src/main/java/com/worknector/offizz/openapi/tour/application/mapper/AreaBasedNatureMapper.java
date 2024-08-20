package com.worknector.offizz.openapi.tour.application.mapper;

import com.worknector.offizz.domain.nature.domain.entity.Nature;
import com.worknector.offizz.openapi.tour.application.dto.TourOpenDataResponse;

public class AreaBasedNatureMapper {

    private AreaBasedNatureMapper() {
        throw new IllegalArgumentException();
    }

    public static Nature toEntity(TourOpenDataResponse.Item item) {
        return Nature.builder()
                .addr1(item.addr1())
                .mapx(item.mapx())
                .mapy(item.mapy())
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
