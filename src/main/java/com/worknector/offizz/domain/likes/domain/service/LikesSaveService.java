package com.worknector.offizz.domain.likes.domain.service;

import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikesSaveService {
    private final LikesRepository likesRepository;

    public void save(Likes likes) {
        likesRepository.save(likes);
    }
}
