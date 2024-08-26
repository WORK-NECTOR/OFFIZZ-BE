package com.worknector.offizz.domain.work.cafe.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.work.cafe.domain.entity.Cafe;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static com.worknector.offizz.domain.work.cafe.domain.entity.QCafe.cafe;
import static com.worknector.offizz.global.util.HaversineUtils.distanceTemplate;

@Repository
@RequiredArgsConstructor
public class CafeDslRepositoryImpl implements CafeDslRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Cafe> findAllPagingBySearchOrLocation(String search, Pageable pageable, double lat, double lon) {
        BooleanBuilder condition = new BooleanBuilder();

        if (search != null) {
            condition.and(searchBuilder(search));
        } else {
            condition.and(locationBuilder(lat, lon));
        }

        List<Cafe> cafes = queryFactory.selectFrom(cafe)
                .where(condition)
                .orderBy(distanceTemplate(lat, lon, cafe.lat, cafe.lon).asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.select(cafe.count())
                .from(cafe)
                .where(condition)
                .fetchOne();

        return new PageImpl<>(cafes, pageable, total);
    }

    private BooleanBuilder locationBuilder(double lat, double lon) {
        return new BooleanBuilder()
                .and(distanceTemplate(lat, lon, cafe.lat, cafe.lon).loe(10.0));
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
}
