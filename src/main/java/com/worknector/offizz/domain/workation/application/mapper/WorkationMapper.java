package com.worknector.offizz.domain.workation.application.mapper;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.application.dto.req.OnboardingRequest;
import com.worknector.offizz.domain.workation.domain.entity.Workation;

public class WorkationMapper {

    private WorkationMapper() {
        throw new IllegalArgumentException();
    }

    public static Workation mapToWorkation(User user, OnboardingRequest onboardingRequest) {
        return Workation.builder()
                .user(user)
                .reason(onboardingRequest.reason())
                .locate(onboardingRequest.locate())
                .address(onboardingRequest.address())
                .startDate(onboardingRequest.startDate())
                .endDate(onboardingRequest.endDate())
                .startCoreTime(onboardingRequest.startCoreTime())
                .endCoreTime(onboardingRequest.endCoreTime())
                .goal(onboardingRequest.goal())
                .build();
    }
}
