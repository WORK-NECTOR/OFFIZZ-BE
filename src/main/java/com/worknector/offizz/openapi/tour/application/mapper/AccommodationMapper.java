package com.worknector.offizz.openapi.tour.application.mapper;

import com.worknector.offizz.openapi.tour.application.dto.AccommodationResponse;
import com.worknector.offizz.openapi.tour.domain.entity.Accommodation;

public class AccommodationMapper {

    private AccommodationMapper() {
        throw new IllegalArgumentException();
    }

    public static Accommodation toEntity(AccommodationResponse.Item item) {
        return Accommodation.builder()
                .addr1(item.getAddr1())
                .cpyrhtDivCd(item.getCpyrhtDivCd())
                .mapy(item.getMapy())
                .mlevel(item.getMlevel())
                .modifiedtime(item.getModifiedtime())
                .sigungucode(item.getSigungucode())
                .tel(item.getTel())
                .title(item.getTitle())
                .contentid(item.getContentid())
                .contenttypeid(item.getContenttypeid())
                .createdtime(item.getCreatedtime())
                .benikia(item.getBenikia())
                .goodstay(item.getGoodstay())
                .hanok(item.getHanok())
                .firstimage(item.getFirstimage())
                .firstimage2(item.getFirstimage2())
                .mapx(item.getMapx())
                .addr2(item.getAddr2())
                .areacode(item.getAreacode())
                .booktour(item.getBooktour())
                .cat1(item.getCat1())
                .cat2(item.getCat2())
                .cat3(item.getCat3())
                .build();
    }
}
