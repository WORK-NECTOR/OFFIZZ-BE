package com.worknector.offizz.openapi.tour.application.mapper;

import com.worknector.offizz.domain.vacation.domain.entity.AreaContent;
import com.worknector.offizz.openapi.tour.application.dto.AreaContentResponse;

public class AreaContentMapper {

    private AreaContentMapper() {
        throw new IllegalArgumentException();
    }

    public static AreaContent toEntity(AreaContentResponse.Item item) {
        return AreaContent.builder()
                .addr1(item.addr1())
                .addr2(item.addr2())
                .mapx(item.mapx())
                .mapy(item.mapy())
                .mlevel(item.mlevel())
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
                .booktour(item.booktour())
                .cat1(item.cat1())
                .cat2(item.cat2())
                .cat3(item.cat3())
                .build();
    }
}
