package com.worknector.offizz.domain.vacation.recommend.application.mapper;

import com.worknector.offizz.domain.vacation.nature.domain.entity.Course;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Nature;
import com.worknector.offizz.domain.vacation.recommend.application.dto.res.VacationRecommendResponse;
import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VacationRecommendMapper {

    public static VacationRecommendResponse fromNature(Nature nature) {
        return new VacationRecommendResponse(
                nature.getNatureId(),
                nature.getAddr1(),
                nature.getLon(),
                nature.getLat(),
                nature.getTitle(),
                nature.getFirstimage(),
                nature.getFirstimage2()
        );
    }

    public static VacationRecommendResponse fromCourse(Course course) {
        return new VacationRecommendResponse(
                course.getCourseId(),
                course.getSigun(),
                course.getLon(),
                course.getLat(),
                course.getCrsKorNm(),
                null,
                null
        );
    }

    public static VacationRecommendResponse fromRestaurant(Restaurant restaurant) {
        return new VacationRecommendResponse(
                restaurant.getRestaurantId(),
                restaurant.getAddr1(),
                restaurant.getLon(),
                restaurant.getLat(),
                restaurant.getTitle(),
                restaurant.getFirstimage(),
                restaurant.getFirstimage2()
        );
    }
}
