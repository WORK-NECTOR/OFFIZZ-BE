package com.worknector.offizz.domain.workation.presentation;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.application.dto.req.EntireRetrospectRequest;
import com.worknector.offizz.domain.workation.application.dto.res.RecapResponse;
import com.worknector.offizz.domain.workation.application.usecase.EntireRetrospectUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/entire/retrospect/")
public class EntireRetrospectController {
    private final EntireRetrospectUseCase retrospectUseCase;

    @PostMapping("/recap")
    public ResponseEntity<RecapResponse> workationFinish(@AuthenticationPrincipal User user, @RequestBody EntireRetrospectRequest request) {
        RecapResponse recapResponse = retrospectUseCase.saveRetrospect(user, request);
        return ResponseEntity.ok(recapResponse);
    }
}
