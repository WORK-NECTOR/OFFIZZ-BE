package com.worknector.offizz.domain.auth.application.usecase.oauth;

import com.worknector.offizz.domain.auth.application.dto.dto.req.CodeRequest;
import com.worknector.offizz.domain.auth.application.dto.dto.res.AuthUserResponse;

public interface SignInUseCase {
    AuthUserResponse getUser(CodeRequest request);
}
