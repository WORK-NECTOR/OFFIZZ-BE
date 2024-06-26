package com.worknector.offizz.domain.user.domain.service;

import com.worknector.offizz.domain.user.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateService {
    public void updateDelete(User user) {
        user.changeStatusToDeleted();
    }
}
