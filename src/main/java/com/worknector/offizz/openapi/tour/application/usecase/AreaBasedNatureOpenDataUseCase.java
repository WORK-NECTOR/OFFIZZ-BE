package com.worknector.offizz.openapi.tour.application.usecase;

import com.worknector.offizz.domain.nature.domain.entity.Nature;
import com.worknector.offizz.domain.nature.domain.repository.NatureRepository;
import com.worknector.offizz.openapi.tour.application.dto.AreaBasedNatureResponse;
import com.worknector.offizz.openapi.tour.application.mapper.AreaBasedNatureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AreaBasedNatureOpenDataUseCase {

    private final TourOpenApiUseCase tourOpenApiUseCase;
    private final NatureRepository natureRepository;

    public void updateAreaBasedNatureData() {
        int pageNo = 1;
        int numOfRows = 100;
        List<Nature> natureEntityList = new ArrayList<>();
        boolean continueFlag = true;

        while (continueFlag) {
            AreaBasedNatureResponse areaBasedNatureResponse = tourOpenApiUseCase.fetchAreaBasedListNatureData(pageNo, numOfRows);

            AreaBasedNatureResponse.Items items = areaBasedNatureResponse.response().body().items();
            int currentRows = areaBasedNatureResponse.response().body().numOfRows();
            int totalCount = areaBasedNatureResponse.response().body().totalCount();

            if (items == null || currentRows == 0) {
                break;
            }

            List<AreaBasedNatureResponse.Item> natureItemList = items.item();
            if (natureItemList.isEmpty()) {
                break;
            }

            for (AreaBasedNatureResponse.Item item : natureItemList) {
                Optional<Nature> existingNature = natureRepository.findByContentid(item.contentid());

                if (existingNature.isPresent()) {
                    // TODO: 기존 데이터 업데이트 로직 추가
                    continue;
                }

                // 새 데이터 삽입
                Nature natureEntity = AreaBasedNatureMapper.toEntity(item);
                natureEntityList.add(natureEntity);
            }

            int lastPage = (int) Math.ceil((double) totalCount / numOfRows);
            if (pageNo >= lastPage) {
                continueFlag = false;
            }

            pageNo++;
        }

        if (!natureEntityList.isEmpty()) {
            natureRepository.saveAll(natureEntityList);
        }
    }
}
