package com.worknector.offizz.domain.vacation.restaurant.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static com.worknector.offizz.domain.vacation.restaurant.domain.entity.QRestaurant.restaurant;
import static com.worknector.offizz.global.util.HaversineUtils.distanceTemplate;

@Repository
@RequiredArgsConstructor
public class RestaurantDslRepositoryImpl implements RestaurantDslRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Restaurant> getAllRestaurantBySearch(String search, Pageable pageable, double lat, double lon) {
        List<Restaurant> restaurants = queryFactory.selectFrom(restaurant)
                .where(searchBuilder(search))
                .orderBy(distanceTemplate(lat, lon, restaurant.lat, restaurant.lon).asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory.select(restaurant.count())
                .from(restaurant)
                .where(searchBuilder(search))
                .fetchOne();
        long total = (count != null) ? count : 0L;

        return new PageImpl<>(restaurants, pageable, total);
    }

    private BooleanBuilder searchBuilder(String search) {
        if (search.isBlank())
            return null;
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
}
