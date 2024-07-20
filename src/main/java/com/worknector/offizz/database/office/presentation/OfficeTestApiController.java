package com.worknector.offizz.database.office.presentation;

import com.worknector.offizz.database.office.application.usecase.OfficeOpenDataUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OfficeTestApiController {

  private final OfficeOpenDataUseCase officeOpenDataUseCase;

  @GetMapping("/test/office")
  public void updateOfficeData() {
    officeOpenDataUseCase.updateOfficeData();
  }
}
