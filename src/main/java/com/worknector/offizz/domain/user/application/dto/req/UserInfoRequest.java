package com.worknector.offizz.domain.user.application.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UserInfoRequest(
        @NotBlank
        @Schema(description = "닉네임", example = "수정하려는 닉네임")
        String nickName
) {
}
