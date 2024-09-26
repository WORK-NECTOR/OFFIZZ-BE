package com.worknector.offizz.domain.likes.domain.service;

import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.repository.LikesRepository;
import com.worknector.offizz.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.worknector.offizz.domain.likes.domain.entity.LikesCategory.*;

@Service
@RequiredArgsConstructor
public class LikesGetService {
    private final LikesRepository likesRepository;

    public List<Likes> findWorkLikes(User user) {
        return likesRepository.findAllByUserAndCategory(user, List.of(office, cafe));
    }

    public List<Likes> findVacationLikes(User user) {
        return likesRepository.findAllByUserAndCategory(user, List.of(nature, course, culture, restaurant, shopping));
    }
}
