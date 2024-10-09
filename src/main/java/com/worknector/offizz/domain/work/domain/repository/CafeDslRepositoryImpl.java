package com.worknector.offizz.domain.work.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.entity.LikesCategory;
import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.work.application.dto.res.SelectCafe;
import com.worknector.offizz.domain.work.domain.entity.Cafe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.worknector.offizz.domain.likes.domain.entity.QLikes.likes;
import static com.worknector.offizz.domain.work.domain.entity.QCafe.cafe;
import static com.worknector.offizz.global.util.HaversineUtils.distanceTemplate;

@Repository
@RequiredArgsConstructor
public class CafeDslRepositoryImpl implements CafeDslRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<SelectCafe> findAllPagingBySearchOrLocation(String search, double lat, double lon, User user) {
        BooleanBuilder condition = new BooleanBuilder();

        if (search != null) {
            condition.and(searchBuilder(search));
        } else {
            condition.and(locationBuilder(lat, lon));
        }

        List<Tuple> tuples = queryFactory.select(cafe, likes)
                .from(cafe)
                .distinct()
                .where(condition)
                .leftJoin(likes)
                .on(likes.likesCategory.eq(LikesCategory.cafe)
                        .and(likes.fkId.eq(cafe.cafeId))
                        .and(likes.user.eq(user)))
                .fetchJoin()
                .orderBy(distanceTemplate(lat, lon, cafe.lat, cafe.lon).asc())
                .fetch();

        return getSelectCafes(tuples);
    }

    private BooleanBuilder locationBuilder(double lat, double lon) {
        return new BooleanBuilder()
                .and(distanceTemplate(lat, lon, cafe.lat, cafe.lon).loe(1.0));
    }

    private BooleanBuilder searchBuilder(String search) {
        if (search.isBlank())
            return null;
        String[] searches = search.split(" ");

        BooleanBuilder builder = new BooleanBuilder();

        Arrays.stream(searches).forEach(se -> {
            BooleanBuilder subBuilder = new BooleanBuilder();
            subBuilder.or(cafe.streetAddress.contains(se))
                    .or(cafe.cafeName.contains(se));
            builder.and(subBuilder);
        });

        return builder;
    }

    private static List<SelectCafe> getSelectCafes(List<Tuple> tuples) {
        List<SelectCafe> selectCafes = new ArrayList<>();

        for (Tuple tuple : tuples) {
            if (tuple.get(likes) == null) {
                selectCafes.add(new SelectCafe(tuple.get(cafe), false));
                continue;
            }
            selectCafes.add(new SelectCafe(tuple.get(cafe), true));
        }
        return selectCafes;
    }

    @Override
    public List<Cafe> findAllCafeById(List<Likes> likes) {
        return queryFactory.selectFrom(cafe)
                .where(cafe.cafeId.in(likes.stream()
                        .map(Likes::getFkId)
                        .toList()))
                .fetch();
    }

}
