package com.worknector.offizz.global.util.scheduler;

import com.worknector.offizz.openapi.course.application.usecase.CourseOpenDataUseCase;
import com.worknector.offizz.openapi.office.application.usecase.OfficeOpenDataUseCase;
import com.worknector.offizz.openapi.tour.application.usecase.AccommodationOpenDataUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenDataScheduler {

    private final CourseOpenDataUseCase courseOpenDataUseCase;
    private final OfficeOpenDataUseCase officeDataUseCase;
    private final AccommodationOpenDataUseCase accommodationOpenDataUseCase;

    // 공유오피스 데이터 매달 1일 자정마다 update
//    @Scheduled(cron = "0 0 0 1 * ?")
    @Scheduled(fixedDelay = 1000000L)
    public void scheduleOfficeOpenDataUpdate() {
        officeDataUseCase.updateOfficeData();
    }

    // 산책로 데이터 매달 1일 자정마다 update
//    @Scheduled(cron = "0 0 0 1 * ?")
    @Scheduled(fixedDelay = 1000000L)
    public void scheduleCourseOpenDataUpdate() {
        courseOpenDataUseCase.updateCourseData();
    }

    // 숙박시설 데이터 매달 1일 자정마다 update
//    @Scheduled(cron = "0 0 0 1 * ?")
    @Scheduled(fixedDelay = 1000000L)
    public void scheduleAccommodationOpenDataUpdate() {
        accommodationOpenDataUseCase.updateAccommodationData();
    }

    // 카페 데이터 매달 1일 자정마다 update
//    @Scheduled(cron = "0 0 0 1 * ?")
    @Scheduled(fixedDelay = 1000000L)
    public void scheduleCafeUpdate() {
        accommodationOpenDataUseCase.updateCafeData();
    }
}
