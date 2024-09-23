package com.worknector.offizz.domain.work.domain.repository;

import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.work.application.dto.res.SelectCafe;
import com.worknector.offizz.domain.work.domain.entity.Cafe;

import java.util.List;

public interface CafeDslRepository {
    List<SelectCafe> findAllPagingBySearchOrLocation(String search, double lat, double lon, User user);

    List<Cafe> findAllCafeById(List<Likes> likes);
}
