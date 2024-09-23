package com.worknector.offizz.domain.likes.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.entity.LikesCategory;
import com.worknector.offizz.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.worknector.offizz.domain.likes.domain.entity.QLikes.likes;

@Repository
@RequiredArgsConstructor
public class LikesDslRepositoryImpl implements LikesDslRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Likes> findAllByUserAndCategory(User user, List<LikesCategory> likesCategories) {
        return queryFactory.selectFrom(likes)
                .where(likes.user.eq(user)
                        .and(likesCategoryBuilder(likesCategories)))
                .fetch();
    }

    private BooleanBuilder likesCategoryBuilder(List<LikesCategory> likesCategories) {
        BooleanBuilder builder = new BooleanBuilder();
        likesCategories.forEach(
                category -> builder.or(likes.likesCategory.eq(category))
        );
        return builder;
    }
}
