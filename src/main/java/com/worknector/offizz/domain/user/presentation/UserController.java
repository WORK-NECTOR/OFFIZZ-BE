package com.worknector.offizz.domain.user.presentation;

import com.worknector.offizz.domain.user.application.dto.res.UserInfoResponse;
import com.worknector.offizz.domain.user.application.usecase.UserUseCase;
import com.worknector.offizz.domain.user.domain.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "User Controller")
public class UserController {

    private final UserUseCase userUseCase;

    @GetMapping
    @Operation(summary = "마이페이지 > 개인정보 조회", description = "토큰으로 요청 -> 개인정보 반환")
    public ResponseEntity<UserInfoResponse> getUserInfo(@AuthenticationPrincipal User user) {
        UserInfoResponse userInfo = userUseCase.getUserInfo(user);
        return ResponseEntity.ok(userInfo);
    }
}
