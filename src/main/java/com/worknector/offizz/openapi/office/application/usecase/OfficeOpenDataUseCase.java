package com.worknector.offizz.openapi.office.application.usecase;

import com.worknector.offizz.openapi.office.application.dto.OfficeResponse;
import com.worknector.offizz.openapi.office.application.mapper.OfficeMapper;
import com.worknector.offizz.domain.work.office.domain.entity.Office;
import com.worknector.offizz.domain.work.office.domain.repository.OfficeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OfficeOpenDataUseCase {

  private final OfficeOpenApiUseCase officeOpenApiUseCase;
  private final OfficeRepository officeRepository;

  public void updateOfficeData() {
    int page = 1;
    int perPage = 100;
    List<Office> officeEntityList = new ArrayList<>();

    while (true) {
      OfficeResponse officeResponse = officeOpenApiUseCase.fetchOfficeData(page, perPage);
      if (officeResponse == null || officeResponse.data().isEmpty()) {
        break;
      }

      List<OfficeResponse.OfficeData> offices = officeResponse.data();
      for (OfficeResponse.OfficeData officeData : offices) {
        Optional<Office> existingOffice = officeRepository.findByOfficeNameAndStreetAddress(
                officeData.officeName(), officeData.streetAddress()
        );

        if (existingOffice.isPresent()) {
          Office existingEntity = existingOffice.get();
          Office updatedEntity = OfficeMapper.toEntity(officeData);
          // TODO: 기존 데이터 업데이트 로직 추가

          continue;
        }

        // 새 데이터 삽입
        Office officeEntity = OfficeMapper.toEntity(officeData);
        officeEntityList.add(officeEntity);
      }

      page++;
    }

    if (!officeEntityList.isEmpty()) {
      officeRepository.saveAll(officeEntityList);
    }
  }
}
