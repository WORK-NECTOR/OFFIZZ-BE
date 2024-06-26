package com.worknector.offizz.global.config.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worknector.offizz.global.exception.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.worknector.offizz.domain.auth.presentation.constant.AuthResponseCode.AUTH_DENIED;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        log.error("AuthenticationEntryPoint : {} {}", null, request.getRequestURI());
        objectMapper.writeValue(
                response.getOutputStream(),
                new ExceptionResponse(AUTH_DENIED.getCode(), AUTH_DENIED.getMessage())
        );
    }
}
