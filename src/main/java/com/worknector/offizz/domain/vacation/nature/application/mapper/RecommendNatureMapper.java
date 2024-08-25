package com.worknector.offizz.domain.vacation.nature.application.mapper;

import com.worknector.offizz.domain.vacation.nature.application.dto.res.RecommendNature;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Course;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Nature;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecommendNatureMapper {

    public static RecommendNature fromNature(Nature nature) {
        return new RecommendNature(
                new RecommendNature.Natures(
                        nature.getNatureId(),
                        nature.getAddr1(),
                        nature.getMapx(),
                        nature.getMapy(),
                        nature.getTel(),
                        nature.getTitle(),
                        nature.getFirstimage(),
                        nature.getFirstimage2(),
                        nature.getHit()
                ),
                null
        );
    }

    public static RecommendNature fromCourse(Course course) {
        return new RecommendNature(
                null,
                new RecommendNature.Courses(
                        course.getCourseId(),
                        course.getCrsContents(),
                        course.getTravelerinfo(),
                        course.getCrsTourInfo(),
                        course.getCrsSummary(),
                        course.getCrsKorNm(),
                        course.getCrsDstnc(),
                        course.getCrsTotlRqrmHour(),
                        course.getGpxpath(),
                        course.getHit()
                )
        );
    }
}
