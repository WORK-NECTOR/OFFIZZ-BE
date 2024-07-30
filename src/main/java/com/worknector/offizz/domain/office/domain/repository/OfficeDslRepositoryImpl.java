package com.worknector.offizz.domain.office.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
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
    public List<Office> findRecommendByRegion(String region) {
        return queryFactory.selectFrom(office)
                .where(office.streetAddress.contains(region))
                .orderBy(office.hit.desc())
                .fetch();
    }

    @Override
    public Page<Office> findAllPagingByRegion(String region, Pageable pageable) {
        List<Office> offices = queryFactory.selectFrom(office)
                .where(office.streetAddress.contains(region))
                .orderBy(office.hit.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(office.count())
                .from(office)
                .where(office.streetAddress.contains(region))
                .fetchOne();

        return new PageImpl<>(offices, pageable, total);
    }
}
