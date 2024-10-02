package com.worknector.offizz.domain.workation.presentation;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.application.dto.req.EntireRetrospectRequest;
import com.worknector.offizz.domain.workation.application.dto.res.AllRecapResponse;
import com.worknector.offizz.domain.workation.application.dto.res.RecapResponse;
import com.worknector.offizz.domain.workation.application.usecase.EntireRetrospectUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/entire")
public class EntireRetrospectController {
    private final EntireRetrospectUseCase retrospectUseCase;

    @PostMapping("/retrospect/recap")
    @Operation(summary = "워케이션 완료 및 리캡 생성", description = "토큰과 전체 회고 (평가 및 내용) 전달해주시면 됩니다")
    public ResponseEntity<RecapResponse> workationFinish(@AuthenticationPrincipal User user, @RequestBody EntireRetrospectRequest request) {
        RecapResponse recapResponse = retrospectUseCase.saveRetrospect(user, request);
        return ResponseEntity.ok(recapResponse);
    }

    @GetMapping("/recap/all")
    @Operation(summary = "지금까지 모든 리캡 조회", description = "토큰과 함께 요청")
    public ResponseEntity<List<AllRecapResponse>> allRecap(@AuthenticationPrincipal User user) {
        List<AllRecapResponse> allRecap = retrospectUseCase.findAllRecap(user);
        return ResponseEntity.ok(allRecap);
    }

    @GetMapping("/recap/{workationId}")
    @Operation(summary = "리캡 상세 조회", description = "토큰 필요, url에 workationId 추가하여 요청")
    public ResponseEntity<RecapResponse> recapDetail(@AuthenticationPrincipal User user, @PathVariable long workationId) {
        RecapResponse recap = retrospectUseCase.getRecap(workationId);
        return ResponseEntity.ok(recap);
    }
}
