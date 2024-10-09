package com.worknector.offizz.domain.vacation.shopping.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.entity.LikesCategory;
import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;
import com.worknector.offizz.domain.vacation.shopping.domain.entity.Shopping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static com.worknector.offizz.domain.likes.domain.entity.QLikes.likes;
import static com.worknector.offizz.domain.vacation.shopping.domain.entity.QShopping.shopping;
import static com.worknector.offizz.global.util.HaversineUtils.distanceTemplate;

@Repository
@RequiredArgsConstructor
public class ShoppingDslRepositoryImpl implements ShoppingDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<VacationRecommendProjection> getAllShoppingBySearch(String search, double lat, double lon, Long userId) {
        BooleanBuilder condition = new BooleanBuilder();

        if (search != null) {
            condition.and(searchBuilder(search));
        } else {
            condition.and(locationBuilder(lat, lon));
        }

        return jpaQueryFactory.select(
                        Projections.constructor(
                                VacationRecommendProjection.class,
                                Expressions.asString("shopping").as("category"),
                                shopping.shoppingId.as("objectPk"),
                                shopping.addr1.as("addr"),
                                shopping.lon,
                                shopping.lat,
                                shopping.title,
                                shopping.firstimage,
                                likes.isNotNull().as("isLike")
                        )
                )
                .from(shopping)
                .leftJoin(likes)
                .on(likes.likesCategory.eq(LikesCategory.shopping)
                        .and(likes.fkId.eq(shopping.shoppingId))
                        .and(likes.user.userId.eq(userId)))
                .fetchJoin()
                .where(condition)
                .orderBy(distanceTemplate(lat, lon, shopping.lat, shopping.lon).asc())
                .fetch();
    }

    private BooleanBuilder searchBuilder(String search) {
        String[] searches = search.split(" ");

        BooleanBuilder builder = new BooleanBuilder();

        Arrays.stream(searches).forEach(se -> {
            BooleanBuilder subBuilder = new BooleanBuilder();
            subBuilder.or(shopping.addr1.contains(se))
                    .or(shopping.title.contains(se));
            builder.and(subBuilder);
        });

        return builder;
    }

    private BooleanBuilder locationBuilder(double lat, double lon) {
        return new BooleanBuilder()
                .and(distanceTemplate(lat, lon, shopping.lat, shopping.lon).loe(1.0));
    }

    @Override
    public List<Shopping> findAllShoppingById(List<Likes> likes) {
        return jpaQueryFactory.selectFrom(shopping)
                .where(shopping.shoppingId.in(likes.stream()
                        .map(Likes::getFkId)
                        .toList()))
                .fetch();
    }
}
