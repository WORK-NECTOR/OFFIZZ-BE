package com.worknector.offizz.domain.auth.application.dto.dto.req;

import jakarta.validation.constraints.NotBlank;

public record CodeRequest(@NotBlank String code) {
}
