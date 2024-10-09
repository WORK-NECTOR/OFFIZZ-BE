package com.worknector.offizz.domain.vacation.restaurant.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.entity.LikesCategory;
import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;
import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static com.worknector.offizz.domain.likes.domain.entity.QLikes.likes;
import static com.worknector.offizz.domain.vacation.restaurant.domain.entity.QRestaurant.restaurant;
import static com.worknector.offizz.global.util.HaversineUtils.distanceTemplate;

@Repository
@RequiredArgsConstructor
public class RestaurantDslRepositoryImpl implements RestaurantDslRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<VacationRecommendProjection> getAllRestaurantBySearch(String search, double lat, double lon, Long userId) {
        BooleanBuilder condition = new BooleanBuilder();

        if (search != null) {
            condition.and(searchBuilder(search));
        } else {
            condition.and(locationBuilder(lat, lon));
        }

        return queryFactory.select(
                        Projections.constructor(
                                VacationRecommendProjection.class,
                                Expressions.asString("restaurant").as("category"),
                                restaurant.restaurantId.as("objectPk"),
                                restaurant.addr1.as("addr"),
                                restaurant.lon,
                                restaurant.lat,
                                restaurant.title,
                                restaurant.firstimage,
                                likes.isNotNull().as("isLike")
                        )
                )
                .from(restaurant)
                .leftJoin(likes)
                .on(likes.likesCategory.eq(LikesCategory.restaurant)
                        .and(likes.fkId.eq(restaurant.restaurantId))
                        .and(likes.user.userId.eq(userId)))
                .fetchJoin()
                .where(condition)
                .orderBy(distanceTemplate(lat, lon, restaurant.lat, restaurant.lon).asc())
                .fetch();
    }

    private BooleanBuilder searchBuilder(String search) {
        String[] searches = search.split(" ");

        BooleanBuilder builder = new BooleanBuilder();

        Arrays.stream(searches).forEach(se -> {
            BooleanBuilder subBuilder = new BooleanBuilder();
            subBuilder.or(restaurant.addr1.contains(se))
                    .or(restaurant.title.contains(se));
            builder.and(subBuilder);
        });

        return builder;
    }

    private BooleanBuilder locationBuilder(double lat, double lon) {
        return new BooleanBuilder()
                .and(distanceTemplate(lat, lon, restaurant.lat, restaurant.lon).loe(1.0));
    }

    @Override
    public List<Restaurant> findAllRestaurantById(List<Likes> likes) {
        return queryFactory.selectFrom(restaurant)
                .where(restaurant.restaurantId.in(likes.stream()
                        .map(Likes::getFkId)
                        .toList()))
                .fetch();
    }
}
