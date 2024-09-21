package com.worknector.offizz.domain.likes.application.mapper;

import com.worknector.offizz.domain.likes.application.dto.req.Like;
import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.user.domain.entity.User;

public class LikeMapper {
    public static Likes mapToLikes(User user, Like like) {
        return Likes.builder()
                .likesCategory(like.category())
                .fkId(like.id())
                .user(user)
                .build();
    }
}
