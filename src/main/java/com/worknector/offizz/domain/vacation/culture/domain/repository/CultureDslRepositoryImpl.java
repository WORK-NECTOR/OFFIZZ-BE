package com.worknector.offizz.domain.vacation.culture.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.vacation.culture.domain.entity.Culture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static com.worknector.offizz.domain.vacation.culture.domain.entity.QCulture.culture;
import static com.worknector.offizz.global.util.HaversineUtils.distanceTemplate;

@Repository
@RequiredArgsConstructor
public class CultureDslRepositoryImpl implements CultureDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Culture> getAllCultureBySearch(String search, double lat, double lon) {
        BooleanBuilder condition = new BooleanBuilder();

        if (search != null) {
            condition.and(searchBuilder(search));
        } else {
            condition.and(locationBuilder(lat, lon));
        }

        return jpaQueryFactory.selectFrom(culture)
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
                .and(distanceTemplate(lat, lon, culture.lat, culture.lon).loe(10.0));
    }
}
