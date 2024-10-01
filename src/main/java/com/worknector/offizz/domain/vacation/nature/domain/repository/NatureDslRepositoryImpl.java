package com.worknector.offizz.domain.vacation.nature.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.entity.LikesCategory;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Nature;
import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static com.worknector.offizz.domain.likes.domain.entity.QLikes.likes;
import static com.worknector.offizz.domain.vacation.nature.domain.entity.QNature.nature;
import static com.worknector.offizz.global.util.HaversineUtils.distanceTemplate;

@Repository
@RequiredArgsConstructor
public class NatureDslRepositoryImpl implements NatureDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<VacationRecommendProjection> findAllNatureBySearch(String search, double lat, double lon, Long userId) {
        BooleanBuilder condition = new BooleanBuilder();

        if (search != null) {
            condition.and(searchBuilder(search));
        } else {
            condition.and(locationBuilder(lat, lon));
        }

        return jpaQueryFactory.select(
                        Projections.constructor(
                                VacationRecommendProjection.class,
                                Expressions.asString("nature").as("category"),
                                nature.natureId.as("objectPk"),
                                nature.addr1.as("addr"),
                                nature.lon,
                                nature.lat,
                                nature.title,
                                nature.firstimage,
                                likes.isNotNull().as("isLike")
                        )
                )
                .from(nature)
                .leftJoin(likes)
                .on(likes.likesCategory.eq(LikesCategory.nature)
                        .and(likes.fkId.eq(nature.natureId))
                        .and(likes.user.userId.eq(userId)))
                .fetchJoin()
                .where(condition)
                .orderBy(distanceTemplate(lat, lon, nature.lat, nature.lon).asc())
                .fetch();
    }

    private BooleanBuilder searchBuilder(String search) {
        String[] searches = search.split(" ");

        BooleanBuilder builder = new BooleanBuilder();

        Arrays.stream(searches).forEach(se -> {
            BooleanBuilder subBuilder = new BooleanBuilder();
            subBuilder.or(nature.addr1.contains(se))
                    .or(nature.title.contains(se));
            builder.and(subBuilder);
        });

        return builder;
    }

    private BooleanBuilder locationBuilder(double lat, double lon) {
        return new BooleanBuilder()
                .and(distanceTemplate(lat, lon, nature.lat, nature.lon).loe(10.0));
    }

    @Override
    public List<Nature> findAllNatureId(List<Likes> likes) {
        return jpaQueryFactory.selectFrom(nature)
                .where(nature.natureId.in(likes.stream()
                        .map(Likes::getFkId)
                        .toList()))
                .fetch();
    }
}
