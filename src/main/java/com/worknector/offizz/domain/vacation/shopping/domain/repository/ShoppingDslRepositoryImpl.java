package com.worknector.offizz.domain.vacation.shopping.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.vacation.shopping.domain.entity.Shopping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static com.worknector.offizz.domain.vacation.shopping.domain.entity.QShopping.shopping;
import static com.worknector.offizz.global.util.HaversineUtils.distanceTemplate;

@Repository
@RequiredArgsConstructor
public class ShoppingDslRepositoryImpl implements ShoppingDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Shopping> getAllShoppingBySearch(String search, double lat, double lon) {
        BooleanBuilder condition = new BooleanBuilder();

        if (search != null) {
            condition.and(searchBuilder(search));
        } else {
            condition.and(locationBuilder(lat, lon));
        }

        return jpaQueryFactory.selectFrom(shopping)
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
                .and(distanceTemplate(lat, lon, shopping.lat, shopping.lon).loe(10.0));
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
