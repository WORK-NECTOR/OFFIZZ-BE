package com.worknector.offizz.domain.vacation.recommend.application.mapper;

import com.worknector.offizz.domain.vacation.culture.domain.entity.Culture;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Course;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Nature;
import com.worknector.offizz.domain.vacation.recommend.application.dto.res.VacationRecommendDetailResponse;
import com.worknector.offizz.domain.vacation.recommend.application.dto.res.VacationRecommendResponse;
import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;
import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;
import com.worknector.offizz.domain.vacation.shopping.domain.entity.Shopping;
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

    public static VacationRecommendDetailResponse fromCulture(Culture culture) {
        return new VacationRecommendDetailResponse(
                culture.getCultureId(),
                culture.getAddr1(),
                culture.getLon(),
                culture.getLat(),
                culture.getTitle(),
                culture.getFirstimage()
        );
    }

    public static VacationRecommendDetailResponse fromCourse(Course course) {
        return new VacationRecommendDetailResponse(
                course.getCourseId(),
                course.getSigun(),
                course.getLon(),
                course.getLat(),
                course.getCrsKorNm(),
                null
        );
    }

    public static VacationRecommendDetailResponse fromNature(Nature nature) {
        return new VacationRecommendDetailResponse(
                nature.getNatureId(),
                nature.getAddr1(),
                nature.getLon(),
                nature.getLat(),
                nature.getTitle(),
                nature.getFirstimage()
        );
    }

    public static VacationRecommendDetailResponse fromRestaurant(Restaurant restaurant) {
        return new VacationRecommendDetailResponse(
                restaurant.getRestaurantId(),
                restaurant.getAddr1(),
                restaurant.getLon(),
                restaurant.getLat(),
                restaurant.getTitle(),
                restaurant.getFirstimage()
        );
    }

    public static VacationRecommendDetailResponse fromShopping(Shopping shopping) {
        return new VacationRecommendDetailResponse(
                shopping.getShoppingId(),
                shopping.getAddr1(),
                shopping.getLon(),
                shopping.getLat(),
                shopping.getTitle(),
                shopping.getFirstimage()
        );
    }
}
