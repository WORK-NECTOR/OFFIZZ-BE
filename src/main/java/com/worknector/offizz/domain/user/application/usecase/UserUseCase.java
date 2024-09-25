package com.worknector.offizz.domain.user.application.usecase;

import com.worknector.offizz.domain.user.application.dto.req.UserInfoRequest;
import com.worknector.offizz.domain.user.application.dto.res.UserInfoResponse;
import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.user.domain.service.UserGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.worknector.offizz.domain.user.application.mapper.UserInfoMapper.mapToUserInfoResponse;

@RequiredArgsConstructor
@Service
@Transactional
public class UserUseCase {

    private final UserGetService userGetService;

    public UserInfoResponse getUserInfo(User user) {
        User findUser = userGetService.byUserId(user.getUserId());
        return mapToUserInfoResponse(findUser);
    }

    public UserInfoResponse updateUserInfo(User user, UserInfoRequest userInfoRequest) {
        User findUser = userGetService.byUserId(user.getUserId());
        findUser.updateNickName(userInfoRequest.nickName());
        return mapToUserInfoResponse(findUser);
    }

    public Long withdrawUser(User user) {
        user.changeStatusToDeleted();
        return user.getUserId();
    }
}
