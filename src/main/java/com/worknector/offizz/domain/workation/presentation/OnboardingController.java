package com.worknector.offizz.domain.workation.presentation;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.application.dto.req.OnboardingRequest;
import com.worknector.offizz.domain.workation.application.usecase.OnboardingUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/onboarding")
@Tag(name = "ONBOARDING Controller")
public class OnboardingController {

    private final OnboardingUseCase onboardingUseCase;

    @PostMapping
    @Operation(summary = "온보딩", description = "온보딩 요청으로 워케이션 정보 저장 후 워케이션 id 반환")
    public ResponseEntity<Long> createOnboarding(@AuthenticationPrincipal User user,
                                                 @RequestBody OnboardingRequest onboardingRequest) {
        Long workationId = onboardingUseCase.createOnboarding(user, onboardingRequest);
        return ResponseEntity.ok(workationId);
    }
}
