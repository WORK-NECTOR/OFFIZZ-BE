package com.worknector.offizz.openapi.tour.application.usecase;

import com.worknector.offizz.domain.vacation.domain.entity.AreaContent;
import com.worknector.offizz.domain.vacation.domain.repository.AreaContentRepository;
import com.worknector.offizz.openapi.tour.application.dto.AreaContentResponse;
import com.worknector.offizz.openapi.tour.application.mapper.AreaContentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AreaContentOpenDataUseCase {

    private final TourOpenApiUseCase tourOpenApiUseCase;
    private final AreaContentRepository areaContentRepository;

    public void updateAreaContentData() {
        int pageNo = 1;
        int numOfRows = 100;
        List<AreaContent> areaContentEntityList = new ArrayList<>();
        boolean continueFlag = true;

        while (continueFlag) {
            AreaContentResponse areaContentResponse = tourOpenApiUseCase.fetchAreaBasedListData(pageNo, numOfRows);

            AreaContentResponse.Items items = areaContentResponse.response().body().items();
            int currentRows = areaContentResponse.response().body().numOfRows();
            int totalCount = areaContentResponse.response().body().totalCount();

            if (items == null || currentRows == 0) {
                break;
            }

            List<AreaContentResponse.Item> areaContentItemList = items.item();
            if (areaContentItemList.isEmpty()) {
                break;
            }

            for (AreaContentResponse.Item item : areaContentItemList) {
                Optional<AreaContent> existingAreaContent = areaContentRepository.findByContentid(item.contentid());

                if (existingAreaContent.isPresent()) {
                    // TODO: 기존 데이터 업데이트 로직 추가
                    continue;
                }

                // 새 데이터 삽입
                AreaContent areaContentEntity = AreaContentMapper.toEntity(item);
                areaContentEntityList.add(areaContentEntity);
            }

            int lastPage = (int) Math.ceil((double) totalCount / numOfRows);
            if (pageNo >= lastPage) {
                continueFlag = false;
            }

            pageNo++;
        }

        if (!areaContentEntityList.isEmpty()) {
            areaContentRepository.saveAll(areaContentEntityList);
        }
    }
}
