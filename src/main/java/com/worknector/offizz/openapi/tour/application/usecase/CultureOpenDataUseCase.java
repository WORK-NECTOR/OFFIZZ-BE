package com.worknector.offizz.openapi.tour.application.usecase;

import com.worknector.offizz.domain.vacation.culture.domain.entity.Culture;
import com.worknector.offizz.domain.vacation.culture.domain.repository.CultureRepository;
import com.worknector.offizz.openapi.tour.application.dto.TourOpenDataResponse;
import com.worknector.offizz.openapi.tour.application.mapper.CultureDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CultureOpenDataUseCase {

    private final TourOpenApiUseCase tourOpenApiUseCase;
    private final CultureRepository cultureRepository;

    private static final int NUM_OF_ROWS = 100;

    public void updateCultureData() {
        int pageNo = 1;
        List<Culture> cultureEntityList = new ArrayList<>();
        boolean continueFlag = true;

        while (continueFlag) {
            TourOpenDataResponse tourOpenDataResponse = tourOpenApiUseCase.fetchCultureData(pageNo, NUM_OF_ROWS);

            TourOpenDataResponse.Items items = tourOpenDataResponse.response().body().items();
            int currentRows = tourOpenDataResponse.response().body().numOfRows();
            int totalCount = tourOpenDataResponse.response().body().totalCount();

            if (items == null || currentRows == 0) {
                break;
            }

            List<TourOpenDataResponse.Item> cultureItemList = items.item();
            if (cultureItemList.isEmpty()) {
                break;
            }

            for (TourOpenDataResponse.Item item : cultureItemList) {
                Optional<Culture> existingCulture = cultureRepository.findByContentidAndTitle(item.contentid(), item.title());

                if (existingCulture.isPresent()) {
                    // TODO: 기존 데이터 업데이트 로직 추가
                    continue;
                }

                // 새 데이터 삽입
                Culture cultureEntity = CultureDataMapper.toEntity(item);
                cultureEntityList.add(cultureEntity);
            }

            int lastPage = (int) Math.ceil((double) totalCount / NUM_OF_ROWS);
            if (pageNo >= lastPage) {
                continueFlag = false;
            }

            pageNo++;
        }

        if (!cultureEntityList.isEmpty()) {
            cultureRepository.saveAll(cultureEntityList);
        }
    }
}
