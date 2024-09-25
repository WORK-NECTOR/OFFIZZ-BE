package com.worknector.offizz.domain.vacation.recommend.application.mapper;

import com.worknector.offizz.domain.vacation.culture.domain.entity.Culture;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Course;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Nature;
import com.worknector.offizz.domain.vacation.recommend.application.dto.res.VacationRecommendResponse;
import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;
import com.worknector.offizz.domain.vacation.shopping.domain.entity.Shopping;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VacationRecommendMapper {

    public static VacationRecommendResponse fromNature(Nature nature) {
        return new VacationRecommendResponse(
                "nature",
                nature.getNatureId(),
                nature.getAddr1(),
                nature.getLon(),
                nature.getLat(),
                nature.getTitle(),
                nature.getFirstimage()
        );
    }

    public static VacationRecommendResponse fromCourse(Course course) {
        return new VacationRecommendResponse(
                "nature",
                course.getCourseId(),
                course.getSigun(),
                course.getLon(),
                course.getLat(),
                course.getCrsKorNm(),
                null
        );
    }

    public static VacationRecommendResponse fromRestaurant(Restaurant restaurant) {
        return new VacationRecommendResponse(
                "restaurant",
                restaurant.getRestaurantId(),
                restaurant.getAddr1(),
                restaurant.getLon(),
                restaurant.getLat(),
                restaurant.getTitle(),
                restaurant.getFirstimage()
        );
    }

    public static VacationRecommendResponse fromCulture(Culture culture) {
        return new VacationRecommendResponse(
                "culture",
                culture.getCultureId(),
                culture.getAddr1(),
                culture.getLon(),
                culture.getLat(),
                culture.getTitle(),
                culture.getFirstimage()
        );
    }

    public static VacationRecommendResponse fromShopping(Shopping shopping) {
        return new VacationRecommendResponse(
                "shopping",
                shopping.getShoppingId(),
                shopping.getAddr1(),
                shopping.getLon(),
                shopping.getLat(),
                shopping.getTitle(),
                shopping.getFirstimage()
        );
    }
}
