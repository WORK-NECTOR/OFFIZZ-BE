package com.worknector.offizz.domain.vacation.nature.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Nature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static com.worknector.offizz.domain.vacation.nature.domain.entity.QNature.nature;
import static com.worknector.offizz.global.util.HaversineUtils.distanceTemplate;

@Repository
@RequiredArgsConstructor
public class NatureDslRepositoryImpl implements NatureDslRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Nature> findAllNatureBySearch(String search, double lat, double lon) {
        BooleanBuilder condition = new BooleanBuilder();

        if (search != null) {
            condition.and(searchBuilder(search));
        } else {
            condition.and(locationBuilder(lat, lon));
        }

        return queryFactory.selectFrom(nature)
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
}
