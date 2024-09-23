package com.worknector.offizz.domain.likes.domain.repository;

import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.entity.LikesCategory;
import com.worknector.offizz.domain.user.domain.entity.User;

import java.util.List;

public interface LikesDslRepository {
    List<Likes> findAllByUserAndCategory(User user, List<LikesCategory> likesCategories);
}
