package com.worknector.offizz.domain.vacation.nature.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static com.worknector.offizz.domain.vacation.nature.domain.entity.QCourse.course;
import static com.worknector.offizz.global.util.HaversineUtils.distanceTemplate;

@Repository
@RequiredArgsConstructor
public class CourseDslRepositoryImpl implements CourseDslRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Course> findAllCourseBySearch(String search, double lat, double lon) {
        return queryFactory.selectFrom(course)
                .where(searchBuilder(search))
                .orderBy(distanceTemplate(lat, lon, course.lat, course.lon).asc())
                .fetch();
    }

    private BooleanBuilder searchBuilder(String search) {
        if (search.isBlank()) {
            return null;
        }

        String[] searches = search.split(" ");

        BooleanBuilder builder = new BooleanBuilder();

        Arrays.stream(searches).forEach(se -> {
            BooleanBuilder subBuilder = new BooleanBuilder();
            subBuilder.or(course.sigun.contains(se))
                    .or(course.crsKorNm.contains(se));
            builder.and(subBuilder);
        });

        return builder;
    }
}
