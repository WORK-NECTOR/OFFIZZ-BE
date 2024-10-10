package com.worknector.offizz.domain.auth.application.dto.dto.req;

public record LoginRequest(
        String email,
        String password
) {
}
