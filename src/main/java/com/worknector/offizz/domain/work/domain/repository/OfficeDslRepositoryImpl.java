package com.worknector.offizz.domain.work.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.entity.LikesCategory;
import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.work.application.dto.res.SelectOffice;
import com.worknector.offizz.domain.work.presenation.constant.Region;
import com.worknector.offizz.domain.work.domain.entity.Office;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.worknector.offizz.domain.likes.domain.entity.QLikes.likes;
import static com.worknector.offizz.domain.work.domain.entity.QOffice.office;
import static com.worknector.offizz.domain.work.presenation.constant.Region.findRegionList;
import static com.worknector.offizz.global.util.HaversineUtils.distanceTemplate;

@Repository
@RequiredArgsConstructor
public class OfficeDslRepositoryImpl implements OfficeDslRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Office> findRecommendByRegion(Region region, int size) {
        return queryFactory.selectFrom(office)
                .where(regionBuilder(region))
                .orderBy(office.hit.desc())
                .limit(size)
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
        List<String> regions = findRegionList(region);
        for (String findRegion : regions) {
            builder.or(office.streetAddress.contains(findRegion));
        }
        return builder;
    }

    @Override
    public Page<Office> findAllPagingBySearch(String search, Pageable pageable) {
        List<Office> offices = queryFactory.selectFrom(office)
                .where(searchBuilder(search))
                .orderBy(office.hit.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.select(office.count())
                .from(office)
                .where(searchBuilder(search))
                .fetchOne();

        return new PageImpl<>(offices, pageable, total);
    }

    @Override
    public List<SelectOffice> findAllPagingBySearchOrLocation(String search, double lat, double lon, User user) {
        BooleanBuilder condition = new BooleanBuilder();

        if (search != null) {
            condition.and(searchBuilder(search));
        } else {
            condition.and(locationBuilder(lat, lon));
        }

        List<Tuple> tuples = queryFactory.select(office, likes)
                .from(office)
                .distinct()
                .where(condition)
                .leftJoin(likes)
                .on(likes.likesCategory.eq(LikesCategory.office)
                        .and(likes.fkId.eq(office.officeId))
                        .and(likes.user.eq(user)))
                .fetchJoin()
                .orderBy(distanceTemplate(lat, lon, office.lat, office.lon).asc())
                .fetch();

        return getSelectOffices(tuples);
    }

    private BooleanBuilder locationBuilder(double lat, double lon) {
        return new BooleanBuilder()
                .and(distanceTemplate(lat, lon, office.lat, office.lon).loe(1.0));
    }

    private BooleanBuilder searchBuilder(String search) {
        if (search.isBlank())
            return null;
        String[] searches = search.split(" ");

        BooleanBuilder builder = new BooleanBuilder();

        Arrays.stream(searches).forEach(se -> {
            BooleanBuilder subBuilder = new BooleanBuilder();
            subBuilder.or(office.streetAddress.contains(se))
                    .or(office.officeName.contains(se));
            builder.and(subBuilder);
        });

        return builder;
    }


    private List<SelectOffice> getSelectOffices(List<Tuple> tuples) {
        List<SelectOffice> selectOffices = new ArrayList<>();

        for (Tuple tuple : tuples) {
            if (tuple.get(likes) == null) {
                selectOffices.add(new SelectOffice(tuple.get(office), false));
                continue;
            }
            selectOffices.add(new SelectOffice(tuple.get(office), true));
        }
        return selectOffices;
    }

    @Override
    public List<Office> findAllOfficeById(List<Likes> likes) {
        return queryFactory.selectFrom(office)
                .where(office.officeId.in(likes.stream()
                        .map(Likes::getFkId)
                        .toList()))
                .fetch();
    }
}
