package com.worknector.offizz.domain.vacation.nature.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.likes.domain.entity.Likes;
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
        BooleanBuilder condition = new BooleanBuilder();

        if (search != null) {
            condition.and(searchBuilder(search));
        } else {
            condition.and(locationBuilder(lat, lon));
        }

        return queryFactory.selectFrom(course)
                .where(condition)
                .orderBy(distanceTemplate(lat, lon, course.lat, course.lon).asc())
                .fetch();
    }

    private BooleanBuilder searchBuilder(String search) {
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

    private BooleanBuilder locationBuilder(double lat, double lon) {
        return new BooleanBuilder()
                .and(distanceTemplate(lat, lon, course.lat, course.lon).loe(10.0));
    }

    @Override
    public List<Course> findAllCourseId(List<Likes> likes) {
        return queryFactory.selectFrom(course)
                .where(course.courseId.in(likes.stream()
                        .map(Likes::getFkId)
                        .toList()))
                .fetch();
    }
}
