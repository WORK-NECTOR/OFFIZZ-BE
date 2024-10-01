package com.worknector.offizz.domain.vacation.nature.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.entity.LikesCategory;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Course;
import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static com.worknector.offizz.domain.likes.domain.entity.QLikes.likes;
import static com.worknector.offizz.domain.vacation.nature.domain.entity.QCourse.course;
import static com.worknector.offizz.global.util.HaversineUtils.distanceTemplate;

@Repository
@RequiredArgsConstructor
public class CourseDslRepositoryImpl implements CourseDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<VacationRecommendProjection> findAllCourseBySearch(String search, double lat, double lon, Long userId) {
        BooleanBuilder condition = new BooleanBuilder();

        if (search != null) {
            condition.and(searchBuilder(search));
        } else {
            condition.and(locationBuilder(lat, lon));
        }

        return jpaQueryFactory.select(
                        Projections.constructor(
                                VacationRecommendProjection.class,
                                Expressions.asString("course").as("category"),
                                course.courseId.as("objectPk"),
                                course.sigun.as("addr"),
                                course.lon,
                                course.lat,
                                course.crsKorNm,
                                Expressions.nullExpression(String.class),
                                likes.isNotNull().as("isLike")
                        )
                )
                .from(course)
                .leftJoin(likes)
                .on(likes.likesCategory.eq(LikesCategory.course)
                        .and(likes.fkId.eq(course.courseId))
                        .and(likes.user.userId.eq(userId)))
                .fetchJoin()
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
        return jpaQueryFactory.selectFrom(course)
                .where(course.courseId.in(likes.stream()
                        .map(Likes::getFkId)
                        .toList()))
                .fetch();
    }
}
