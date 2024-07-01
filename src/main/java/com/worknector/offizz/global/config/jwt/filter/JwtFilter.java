package com.worknector.offizz.global.config.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worknector.offizz.global.config.jwt.JwtUtils;
import com.worknector.offizz.global.config.jwt.constant.Type;
import com.worknector.offizz.global.exception.ApplicationException;
import com.worknector.offizz.global.exception.ExceptionResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final ObjectMapper objectMapper;
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer";
    private static final String CHARACTER_ENCODING = "UTF-8";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);
        if (token != null) {
            log.info("토큰 함께 요청 : {}", token);
            try {
                if (request.getRequestURI().contains("/refresh")) {
                    log.info("재발급 진행");
                    jwtUtils.validateToken(token, Type.REFRESH);
                } else {
                    log.info("일반 접근");
                    jwtUtils.validateToken(token, Type.ACCESS);
                }
                Authentication authentication = jwtUtils.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("context 인증 정보 저장 : {}", authentication.getName());
            } catch (ApplicationException ex) {
                log.error("JWT 필터 예외 처리 : {}", ex.getMessage());
                handleException(response, ex);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            return bearerToken.split(" ")[1];
        }
        return null;
    }


    private void handleException(HttpServletResponse response, ApplicationException ex) throws IOException {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getCode(),
                ex.getMessage()
        );
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(CHARACTER_ENCODING);
        response.getWriter().write(objectMapper.writeValueAsString(exceptionResponse));
    }
}
