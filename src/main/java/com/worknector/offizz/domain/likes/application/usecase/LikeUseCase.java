package com.worknector.offizz.domain.likes.application.usecase;

import com.worknector.offizz.domain.likes.application.dto.req.Like;
import com.worknector.offizz.domain.likes.application.mapper.LikeMapper;
import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.service.LikesSaveService;
import com.worknector.offizz.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class LikeUseCase {
    private final LikesSaveService likesSaveService;

    public void saveWorkLike(User user, Like workLike) {
        Likes likes = LikeMapper.mapToLikes(user, workLike);
        likesSaveService.save(likes);
    }

    public void saveVacationLike(User user, Like vacationLike) {
        Likes likes = LikeMapper.mapToLikes(user, vacationLike);
        likesSaveService.save(likes);
    }
}
