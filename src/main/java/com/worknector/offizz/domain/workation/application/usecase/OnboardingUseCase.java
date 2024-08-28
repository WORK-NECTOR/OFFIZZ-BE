package com.worknector.offizz.domain.workation.application.usecase;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.application.dto.req.OnboardingRequest;
import com.worknector.offizz.domain.workation.application.mapper.BucketlistMapper;
import com.worknector.offizz.domain.workation.application.mapper.WorkationKeywordMapper;
import com.worknector.offizz.domain.workation.application.mapper.WorkationMapper;
import com.worknector.offizz.domain.workation.domain.entity.Bucketlist;
import com.worknector.offizz.domain.workation.domain.entity.Keyword;
import com.worknector.offizz.domain.workation.domain.entity.Workation;
import com.worknector.offizz.domain.workation.domain.entity.WorkationKeyword;
import com.worknector.offizz.domain.workation.domain.service.BucketlistSaveService;
import com.worknector.offizz.domain.workation.domain.service.WorkationKeywordSaveService;
import com.worknector.offizz.domain.workation.domain.service.WorkationSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class OnboardingUseCase {

    private final BucketlistSaveService bucketlistSaveService;
    private final WorkationKeywordSaveService workationKeywordSaveService;
    private final WorkationSaveService workationSaveService;

    /**
     * 온보딩 요청에 대해 워케이션 정보를 저장하고, 키워드 및 버킷리스트 장소를 저장한다.
     * @param user 요청 사용자
     * @param onboardingRequest 온보딩 요청 정보
     * @return 생성된 워케이션 id
     */
    public Long createOnboarding(User user, OnboardingRequest onboardingRequest) {
        Workation workation = saveWorkation(user, onboardingRequest);
        saveWorkationKeywords(workation, onboardingRequest.keywords());
        saveBucketlists(workation, onboardingRequest.bucketlists());
        return workation.getWorkationId();
    }

    private Workation saveWorkation(User user, OnboardingRequest onboardingRequest) {
        Workation workation = WorkationMapper.mapToWorkation(user, onboardingRequest);
        return workationSaveService.save(workation);
    }

    private void saveWorkationKeywords(Workation savedWorkation, List<Keyword> keywords) {
        for (Keyword keyword : keywords) {
            WorkationKeyword workationKeyword = WorkationKeywordMapper.mapToWorkationKeyword(savedWorkation, keyword);
            workationKeywordSaveService.save(workationKeyword);
        }
    }

    private void saveBucketlists(Workation savedWorkation, List<OnboardingRequest.Bucketlist> bucketlists) {
        for (OnboardingRequest.Bucketlist bucketlist : bucketlists) {
            Bucketlist entity = BucketlistMapper.mapToBucketlist(savedWorkation, bucketlist.name(), bucketlist.address());
            bucketlistSaveService.save(entity);
        }
    }
}
