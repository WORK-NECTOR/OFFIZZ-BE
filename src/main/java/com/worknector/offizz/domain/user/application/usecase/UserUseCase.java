package com.worknector.offizz.domain.user.application.usecase;

import com.worknector.offizz.domain.user.application.dto.req.UserInfoRequest;
import com.worknector.offizz.domain.user.application.dto.res.UserInfoResponse;
import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.user.domain.service.UserGetService;
import com.worknector.offizz.domain.workation.domain.service.WorkationGetService;
import com.worknector.offizz.global.exception.ExceptionResponse;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.worknector.offizz.domain.user.application.mapper.UserInfoMapper.mapToUserInfoResponse;
import static com.worknector.offizz.domain.user.presentation.constant.UserResponseCode.USER_DELETED;

@RequiredArgsConstructor
@Service
@Transactional
public class UserUseCase {

    private final UserGetService userGetService;
    private final WorkationGetService workationGetService;

    public UserInfoResponse getUserInfo(User user) {
        User findUser = userGetService.byUserId(user.getUserId());
        validateUsableUser(findUser);
        return mapToUserInfoResponse(findUser);
    }

    public UserInfoResponse updateUserInfo(User user, UserInfoRequest userInfoRequest) {
        User findUser = userGetService.byUserId(user.getUserId());
        validateUsableUser(findUser);
        findUser.updateNickName(userInfoRequest.nickName());
        return mapToUserInfoResponse(findUser);
    }

    public Long withdrawUser(User user) {
        user.changeStatusToDeleted();
        return user.getUserId();
    }

    public boolean getOnGoingWorkation(User user) {
        return !workationGetService.findOnGoingWorkation(user).isEmpty();
    }

    private void validateUsableUser(User user) {
        if (user.isDeleted()) {
            new ExceptionResponse(USER_DELETED.getCode(), USER_DELETED.getMessage());
        }
    }
}
