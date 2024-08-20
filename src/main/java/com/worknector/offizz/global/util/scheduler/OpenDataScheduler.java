package com.worknector.offizz.global.util.scheduler;

import com.worknector.offizz.openapi.course.application.usecase.CourseOpenDataUseCase;
import com.worknector.offizz.openapi.office.application.usecase.OfficeOpenDataUseCase;
import com.worknector.offizz.openapi.tour.application.usecase.AccommodationOpenDataUseCase;
import com.worknector.offizz.openapi.tour.application.usecase.RestaurantOpenDataUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OpenDataScheduler {

    private static final List<String> RESTAURANT_CAT3_LIST = List.of("A05020100", "A05020200", "A05020300", "A05020400", "A05020700");

    private final CourseOpenDataUseCase courseOpenDataUseCase;
    private final OfficeOpenDataUseCase officeDataUseCase;
    private final AccommodationOpenDataUseCase accommodationOpenDataUseCase;
    private final RestaurantOpenDataUseCase restaurantOpenDataUseCase;

    // 공유오피스 데이터 매달 1일 자정마다 update
    @Scheduled(cron = "0 0 0 1 * ?")
    public void scheduleOfficeOpenDataUpdate() {
        officeDataUseCase.updateOfficeData();
    }

    // 산책로 데이터 매달 1일 자정마다 update
    @Scheduled(cron = "0 0 0 1 * ?")
    public void scheduleCourseOpenDataUpdate() {
        courseOpenDataUseCase.updateCourseData();
    }

    // 숙박시설 데이터 매달 1일 자정마다 update
    @Scheduled(cron = "0 0 0 1 * ?")
    public void scheduleAccommodationOpenDataUpdate() {
        accommodationOpenDataUseCase.updateAccommodationData();
    }

    // 음식점 데이터 매달 1일 자정마다 update
    @Scheduled(cron = "0 0 0 1 * ?")
    public void scheduleRestaurantOpenDataUpdate() {
        restaurantOpenDataUseCase.updateRestaurantData(RESTAURANT_CAT3_LIST);
    }
}
