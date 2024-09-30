package com.worknector.offizz.domain.vacation.recommend.application.mapper;

import com.worknector.offizz.domain.vacation.recommend.application.dto.res.VacationRecommendResponse;
import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VacationRecommendMapper {

    public static VacationRecommendResponse fromVacationRecommendProjection(VacationRecommendProjection projection) {
        return new VacationRecommendResponse(
                projection.category(),
                projection.objectPk(),
                projection.addr(),
                projection.lon(),
                projection.lat(),
                projection.title(),
                projection.firstImage(),
                projection.isLike()
        );
    }
}
