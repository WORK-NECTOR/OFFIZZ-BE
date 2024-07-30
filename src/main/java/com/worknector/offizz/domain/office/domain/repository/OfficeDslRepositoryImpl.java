package com.worknector.offizz.domain.office.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.office.application.dto.req.Region;
import com.worknector.offizz.domain.office.domain.entity.Office;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.worknector.offizz.domain.office.domain.entity.QOffice.office;

@Repository
@RequiredArgsConstructor
public class OfficeDslRepositoryImpl implements OfficeDslRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Office> findRecommendByRegion(Region region) {
        return queryFactory.selectFrom(office)
                .where(regionBuilder(region))
                .orderBy(office.hit.desc())
                .fetch();
    }

    @Override
    public Page<Office> findAllPagingByRegion(Region region, Pageable pageable) {
        List<Office> offices = queryFactory.selectFrom(office)
                .where(regionBuilder(region))
                .orderBy(office.hit.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.select(office.count())
                .from(office)
                .where(regionBuilder(region))
                .fetchOne();

        return new PageImpl<>(offices, pageable, total);
    }

    private BooleanBuilder regionBuilder(Region region) {
        BooleanBuilder builder = new BooleanBuilder();
        if (region.isEtc()) {
            for (Region nonEtcRegion : Region.getNonEtcRegions()) {
                builder.and(office.streetAddress.contains(nonEtcRegion.toString()).not());
            }
            return builder;
        }

        return builder.and(office.streetAddress.contains(region.toString()));
    }
}
