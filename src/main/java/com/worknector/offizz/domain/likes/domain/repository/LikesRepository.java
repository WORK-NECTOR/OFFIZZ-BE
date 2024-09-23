package com.worknector.offizz.domain.likes.domain.repository;

import com.worknector.offizz.domain.likes.domain.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long>, LikesDslRepository {
}
