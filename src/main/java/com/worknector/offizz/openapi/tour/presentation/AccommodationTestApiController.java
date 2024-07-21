package com.worknector.offizz.openapi.tour.presentation;

import com.worknector.offizz.openapi.tour.application.usecase.AccommodationOpenDataUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccommodationTestApiController {

  private final AccommodationOpenDataUseCase accommodationOpenDataUseCase;

  @GetMapping("/test/accommodation")
  public void updateCourseData() {
    accommodationOpenDataUseCase.updateAccommodationData();
  }
}
