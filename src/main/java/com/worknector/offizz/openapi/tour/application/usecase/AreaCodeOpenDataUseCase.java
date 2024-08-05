package com.worknector.offizz.openapi.tour.application.usecase;

import com.worknector.offizz.domain.vacation.domain.entity.AreaCode;
import com.worknector.offizz.domain.vacation.domain.repository.AreaCodeRepository;
import com.worknector.offizz.openapi.tour.application.dto.AreaCodeResponse;
import com.worknector.offizz.openapi.tour.application.mapper.AreaCodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AreaCodeOpenDataUseCase {

    private final TourOpenApiUseCase tourOpenApiUseCase;
    private final AreaCodeRepository areaCodeRepository;

    public void updateAreaCodeData() {
        int pageNo = 1;
        int numOfRows = 100;
        List<AreaCode> areaCodeEntityList = new ArrayList<>();
        boolean continueFlag = true;

        while (continueFlag) {
            AreaCodeResponse areaCodeResponse = tourOpenApiUseCase.fetchAreaCodeData(pageNo, numOfRows);

            List<AreaCodeResponse.Item> areaCodeItems = areaCodeResponse.response().body().items().item();
            int totalCount = areaCodeResponse.response().body().totalCount();

            if (areaCodeItems.isEmpty()) {
                break;
            }

            for (AreaCodeResponse.Item item : areaCodeItems) {
                Optional<AreaCode> existingAreaCode = areaCodeRepository.findByName(item.name());

                if (existingAreaCode.isPresent()) {
                    // TODO: 기존 데이터 업데이트 로직 추가
                    continue;
                }

                // 새 데이터 삽입
                AreaCode areaCodeEntity = AreaCodeMapper.toEntity(item);
                areaCodeEntityList.add(areaCodeEntity);
            }

            int lastPage = (int) Math.ceil((double) totalCount / numOfRows);
            if (pageNo >= lastPage) {
                continueFlag = false;
            }

            pageNo++;
        }

        if (!areaCodeEntityList.isEmpty()) {
            areaCodeRepository.saveAll(areaCodeEntityList);
        }
    }
}
