package com.worknector.offizz.domain.auth.presentation;

import com.worknector.offizz.domain.auth.application.dto.dto.req.CodeRequest;
import com.worknector.offizz.domain.auth.application.dto.dto.req.LoginRequest;
import com.worknector.offizz.domain.auth.application.dto.dto.res.AuthResponse;
import com.worknector.offizz.domain.auth.application.dto.dto.res.AuthUserResponse;
import com.worknector.offizz.domain.auth.application.dto.dto.res.JwtTokenResponse;
import com.worknector.offizz.domain.auth.application.usecase.JwtUseCase;
import com.worknector.offizz.domain.auth.application.usecase.oauth.SelectOauth;
import com.worknector.offizz.domain.auth.application.usecase.oauth.SignInUseCase;
import com.worknector.offizz.domain.auth.application.usecase.oauth.self.SelfSignInUseCase;
import com.worknector.offizz.domain.auth.presentation.constant.Provider;
import com.worknector.offizz.domain.user.domain.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "AUTH Controller")
public class AuthController {
    private final SelectOauth selectOauth;
    private final SelfSignInUseCase selfSignInUseCase;
    private final JwtUseCase jwtUseCase;

    @PostMapping("/login/{provider}")
    @Operation(summary = "소셜 로그인", description = "회원이 아닌 경우, 회원가입 후 토큰을 반환합니다.")
    public ResponseEntity<AuthResponse> authLogin(@RequestBody @Valid CodeRequest request, @PathVariable Provider provider) {
        SignInUseCase signInUseCase = selectOauth.selectSignIn(provider);
        AuthUserResponse authUser = signInUseCase.getUser(request);
        JwtTokenResponse jwtToken = jwtUseCase.signIn(authUser.user());
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/login")
    @Operation(summary = "자체 로그인", description = "이메일, 비밀번호 요청 > 회원 아닌 경우 오류 발생")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        User user = selfSignInUseCase.getUser(request);
        JwtTokenResponse jwtToken = jwtUseCase.signIn(user);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/signup")
    @Operation(summary = "자체 회원가입", description = "이메일, 비밀번호 요청 > 회원가입 후 토큰 응답")
    public ResponseEntity<AuthResponse> signUp(@RequestBody LoginRequest request) {
        User user = selfSignInUseCase.signUp(request);
        JwtTokenResponse jwtToken = jwtUseCase.signIn(user);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "토큰 같이 보내주세요")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal User user) {
        jwtUseCase.logout(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    @Operation(summary = "토큰 재발급 | 토큰 필요", description = "refreshToken 으로 토큰 재발급")
    public ResponseEntity<JwtTokenResponse> refresh(@AuthenticationPrincipal User user, HttpServletRequest request) {
        JwtTokenResponse jwtToken = jwtUseCase.regenerateToken(user, request);
        return ResponseEntity.ok(jwtToken);
    }
}
