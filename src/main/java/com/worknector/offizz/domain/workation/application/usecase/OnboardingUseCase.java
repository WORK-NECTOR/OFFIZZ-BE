package com.worknector.offizz.domain.workation.application.usecase;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.workation.application.dto.req.OnboardingRequest;
import com.worknector.offizz.domain.workation.application.mapper.BucketlistMapper;
import com.worknector.offizz.domain.workation.application.mapper.DailyMapper;
import com.worknector.offizz.domain.workation.application.mapper.WorkationKeywordMapper;
import com.worknector.offizz.domain.workation.application.mapper.WorkationMapper;
import com.worknector.offizz.domain.workation.domain.entity.*;
import com.worknector.offizz.domain.workation.domain.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class OnboardingUseCase {

    private final BucketlistSaveService bucketlistSaveService;
    private final WorkationVacationKeywordSaveService workationVacationKeywordSaveService;
    private final WorkationWorkKeywordSaveService workationWorkKeywordSaveService;
    private final WorkationSaveService workationSaveService;
    private final DailySaveService dailySaveService;

    /**
     * 온보딩 요청에 대해 워케이션 정보를 저장하고, 키워드 및 버킷리스트 장소를 저장한다.
     * @param user 요청 사용자
     * @param onboardingRequest 온보딩 요청 정보
     * @return 생성된 워케이션 id
     */
    public Long createOnboarding(User user, OnboardingRequest onboardingRequest) {
        Workation workation = saveWorkation(user, onboardingRequest);
        saveWorkationKeywords(workation, onboardingRequest.workKeywords(), onboardingRequest.vacationKeywords());
        saveBucketlists(workation, onboardingRequest.bucketlists());
        saveDaily(workation);
        return workation.getWorkationId();
    }

    private Workation saveWorkation(User user, OnboardingRequest onboardingRequest) {
        Workation workation = WorkationMapper.mapToWorkation(user, onboardingRequest);
        return workationSaveService.save(workation);
    }

    private void saveWorkationKeywords(Workation savedWorkation,
                                       List<WorkKeyword> workKeywords,
                                       List<VacationKeyword> vacationKeywords) {
        // 일 키워드 저장
        for (WorkKeyword workKeyword : workKeywords) {
            WorkationWorkKeyword workationWorkKeyword = WorkationKeywordMapper
                    .mapToWorkationWorkKeyword(savedWorkation, workKeyword);
            workationWorkKeywordSaveService.save(workationWorkKeyword);
        }

        // 여행 키워드 저장
        for (VacationKeyword vacationKeyword : vacationKeywords) {
            WorkationVacationKeyword workationVacationKeyword = WorkationKeywordMapper
                    .mapToWorkationVacationKeyword(savedWorkation, vacationKeyword);
            workationVacationKeywordSaveService.save(workationVacationKeyword);
        }
    }

    private void saveBucketlists(Workation savedWorkation, List<OnboardingRequest.Bucketlist> bucketlists) {
        for (OnboardingRequest.Bucketlist bucketlist : bucketlists) {
            Bucketlist entity = BucketlistMapper.mapToBucketlist(savedWorkation, bucketlist.name(), bucketlist.address());
            bucketlistSaveService.save(entity);
        }
    }

    private void saveDaily(Workation saveWorkation) {
        Duration between = Duration.between(saveWorkation.getStartDate(), saveWorkation.getEndDate());
        for (int day = 0; day < between.toDays(); day++) {
            Daily daily = DailyMapper.mapToDaily(saveWorkation, day);
            dailySaveService.save(daily);
        }
    }
}
