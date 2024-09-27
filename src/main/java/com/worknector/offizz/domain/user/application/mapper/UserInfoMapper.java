package com.worknector.offizz.domain.user.application.mapper;

import com.worknector.offizz.domain.user.application.dto.res.UserInfoResponse;
import com.worknector.offizz.domain.user.domain.entity.User;

public class UserInfoMapper {
    private UserInfoMapper() {
        throw new IllegalArgumentException();
    }

    public static UserInfoResponse mapToUserInfoResponse(User user) {
        return new UserInfoResponse(user.getNickName(), user.getEmail());
    }
}
