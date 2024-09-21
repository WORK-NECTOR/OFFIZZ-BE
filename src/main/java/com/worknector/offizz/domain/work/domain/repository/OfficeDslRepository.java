package com.worknector.offizz.domain.work.domain.repository;

import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.work.application.dto.res.SelectOffice;
import com.worknector.offizz.domain.work.presenation.constant.Region;
import com.worknector.offizz.domain.work.domain.entity.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OfficeDslRepository {
    List<Office> findRecommendByRegion(Region region, int size);

    Page<Office> findAllPagingByRegion(Region region, Pageable pageable);

    Page<Office> findAllPagingBySearch(String search, Pageable pageable);

    List<SelectOffice> findAllPagingBySearchOrLocation(String search, double lat, double lon, User user);

    List<Office> findAllOfficeById(List<Likes> likes);
}
