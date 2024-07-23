package com.worknector.offizz.openapi.tour.application.mapper;

import com.worknector.offizz.openapi.tour.application.dto.AccommodationResponse;
import com.worknector.offizz.openapi.tour.domain.entity.Accommodation;

public class AccommodationMapper {

    private AccommodationMapper() {
        throw new IllegalArgumentException();
    }

    public static Accommodation toEntity(AccommodationResponse.Item item) {
        return Accommodation.builder()
                .addr1(item.addr1())
                .cpyrhtDivCd(item.cpyrhtDivCd())
                .mapy(item.mapy())
                .mlevel(item.mlevel())
                .modifiedtime(item.modifiedtime())
                .sigungucode(item.sigungucode())
                .tel(item.tel())
                .title(item.title())
                .contentid(item.contentid())
                .contenttypeid(item.contenttypeid())
                .createdtime(item.createdtime())
                .benikia(item.benikia())
                .goodstay(item.goodstay())
                .hanok(item.hanok())
                .firstimage(item.firstimage())
                .firstimage2(item.firstimage2())
                .mapx(item.mapx())
                .addr2(item.addr2())
                .areacode(item.areacode())
                .booktour(item.booktour())
                .cat1(item.cat1())
                .cat2(item.cat2())
                .cat3(item.cat3())
                .build();
    }
}
