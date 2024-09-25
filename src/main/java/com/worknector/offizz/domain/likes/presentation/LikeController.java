package com.worknector.offizz.domain.likes.presentation;

import com.worknector.offizz.domain.likes.application.dto.req.Like;
import com.worknector.offizz.domain.likes.application.usecase.LikeUseCase;
import com.worknector.offizz.domain.user.domain.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/like/")
public class LikeController {
    private final LikeUseCase likeUseCase;

    @PostMapping("vacation")
    @Operation(summary = "nature, course, culture, shopping, restaurant 좋아요 누르는 카테고리에 맞춰서 요청", description = "토큰 함께")
    public ResponseEntity<Void> saveVacationLike(@AuthenticationPrincipal User user, @RequestBody Like vacationLike) {
        likeUseCase.saveVacationLike(user, vacationLike);
        return ResponseEntity.ok().build();
    }

    @PostMapping("work")
    @Operation(summary = "office, cafe 좋아요 누르는 카테고리에 맞춰서 요청", description = "토큰 함께")
    public ResponseEntity<Void> saveWorkLike(@AuthenticationPrincipal User user, @RequestBody Like workLike) {
        likeUseCase.saveWorkLike(user, workLike);
        return ResponseEntity.ok().build();
    }
}
