package com.worknector.offizz.domain.workation.presentation;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.application.dto.res.RetrospectResponse;
import com.worknector.offizz.domain.workation.application.usecase.RetrospectUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/retrospect")
@Tag(name = "Retrospect Controller")
public class RetrospectController {

    private final RetrospectUseCase retrospectUseCase;

    @GetMapping("/{day}")
    @Operation(summary = "회고 > 일자별 회고 조회", description = "토큰 요청")
    public ResponseEntity<RetrospectResponse> getRetrospect(@AuthenticationPrincipal User user, @PathVariable int day) {
        RetrospectResponse retrospectResponse = retrospectUseCase.getRetrospectByDay(user, day);
        return ResponseEntity.ok(retrospectResponse);
    }
}
