package com.worknector.offizz.openapi.tour.application.usecase;

import com.worknector.offizz.openapi.tour.application.dto.AccommodationResponse;
import com.worknector.offizz.openapi.tour.application.mapper.AccommodationMapper;
import com.worknector.offizz.domain.accommodation.domain.entity.Accommodation;
import com.worknector.offizz.domain.accommodation.domain.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccommodationOpenDataUseCase {

    private final TourOpenApiUseCase tourOpenApiUseCase;
    private final AccommodationRepository accommodationRepository;

    public void updateAccommodationData() {
        int pageNo = 1;
        int numOfRows = 100;
        List<Accommodation> accommodationEntityList = new ArrayList<>();

        while (true) {
            AccommodationResponse accommodationResponse = tourOpenApiUseCase.fetchAccommodationData(pageNo, numOfRows);

            AccommodationResponse.Items items = accommodationResponse.response().body().items();
            int currentRows = accommodationResponse.response().body().numOfRows();
            int totalCount = accommodationResponse.response().body().totalCount();

            if (items == null || currentRows == 0) {
                break;
            }

            List<AccommodationResponse.Item> accommodationItemList = items.item();
            if (accommodationItemList.isEmpty()) {
                break;
            }

            for (AccommodationResponse.Item item : accommodationItemList) {
                Optional<Accommodation> existingAccommodation = accommodationRepository.findByContentid(item.contentid());

                if (existingAccommodation.isPresent()) {
                    // TODO: 기존 데이터 업데이트 로직 추가
                    continue;
                }

                // 새 데이터 삽입
                Accommodation accommodationEntity = AccommodationMapper.toEntity(item);
                accommodationEntityList.add(accommodationEntity);
            }

            int lastPage = (int) Math.ceil((double) totalCount / numOfRows);
            if (pageNo >= lastPage) {
                break;
            }

            pageNo++;
        }

        if (!accommodationEntityList.isEmpty()) {
            accommodationRepository.saveAll(accommodationEntityList);
        }
    }
}
