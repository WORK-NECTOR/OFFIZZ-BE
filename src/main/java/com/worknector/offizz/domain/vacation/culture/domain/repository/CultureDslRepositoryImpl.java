package com.worknector.offizz.domain.vacation.culture.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.entity.LikesCategory;
import com.worknector.offizz.domain.vacation.culture.domain.entity.Culture;
import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static com.worknector.offizz.domain.likes.domain.entity.QLikes.likes;
import static com.worknector.offizz.domain.vacation.culture.domain.entity.QCulture.culture;
import static com.worknector.offizz.global.util.HaversineUtils.distanceTemplate;

@Repository
@RequiredArgsConstructor
public class CultureDslRepositoryImpl implements CultureDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<VacationRecommendProjection> getAllCultureBySearch(String search, double lat, double lon, Long userId) {
        BooleanBuilder condition = new BooleanBuilder();

        if (search != null) {
            condition.and(searchBuilder(search));
        } else {
            condition.and(locationBuilder(lat, lon));
        }

        return jpaQueryFactory.select(
                        Projections.constructor(
                                VacationRecommendProjection.class,
                                Expressions.asString("culture").as("category"),
                                culture.cultureId.as("objectPk"),
                                culture.addr1.as("addr"),
                                culture.lon,
                                culture.lat,
                                culture.title,
                                culture.firstimage,
                                likes.isNotNull().as("isLike")
                        )
                )
                .from(culture)
                .leftJoin(likes)
                .on(likes.likesCategory.eq(LikesCategory.culture)
                        .and(likes.fkId.eq(culture.cultureId))
                        .and(likes.user.userId.eq(userId)))
                .fetchJoin()
                .where(condition)
                .orderBy(distanceTemplate(lat, lon, culture.lat, culture.lon).asc())
                .fetch();
    }

    private BooleanBuilder searchBuilder(String search) {
        String[] searches = search.split(" ");

        BooleanBuilder builder = new BooleanBuilder();

        Arrays.stream(searches).forEach(se -> {
            BooleanBuilder subBuilder = new BooleanBuilder();
            subBuilder.or(culture.addr1.contains(se))
                    .or(culture.title.contains(se));
            builder.and(subBuilder);
        });

        return builder;
    }

    private BooleanBuilder locationBuilder(double lat, double lon) {
        return new BooleanBuilder()
                .and(distanceTemplate(lat, lon, culture.lat, culture.lon).loe(1.0));
    }

    @Override
    public List<Culture> findAllCourseId(List<Likes> likes) {
        return jpaQueryFactory.selectFrom(culture)
                .where(culture.cultureId.in(likes.stream()
                        .map(Likes::getFkId)
                        .toList()))
                .fetch();
    }
}
