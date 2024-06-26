package com.worknector.offizz.domain.auth.presentation;

import com.worknector.offizz.domain.auth.application.dto.dto.req.CodeRequest;
import com.worknector.offizz.domain.auth.application.dto.dto.res.AuthResponse;
import com.worknector.offizz.domain.auth.application.dto.dto.res.AuthUserResponse;
import com.worknector.offizz.domain.auth.application.dto.dto.res.JwtTokenResponse;
import com.worknector.offizz.domain.auth.application.usecase.JwtUseCase;
import com.worknector.offizz.domain.auth.application.usecase.oauth.SelectOauth;
import com.worknector.offizz.domain.auth.application.usecase.oauth.SignInUseCase;
import com.worknector.offizz.domain.auth.presentation.constant.Provider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "AUTH Controller")
public class AuthController {
    private final SelectOauth selectOauth;
    private final JwtUseCase jwtUseCase;

    @PostMapping("/login/{provider}")
    @Operation(summary = "소셜 로그인", description = "회원이 아닌 경우, 회원가입 후 토큰을 반환합니다.")
    public ResponseEntity<AuthResponse> authLogin(@RequestBody @Valid CodeRequest request, @PathVariable Provider provider) {
        SignInUseCase signInUseCase = selectOauth.selectSignIn(provider);
        AuthUserResponse authUser = signInUseCase.getUser(request);
        JwtTokenResponse jwtToken = jwtUseCase.signIn(authUser.user());
        return ResponseEntity.ok().body(jwtToken);
    }
}
