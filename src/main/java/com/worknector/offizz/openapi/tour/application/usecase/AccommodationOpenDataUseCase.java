package com.worknector.offizz.openapi.tour.application.usecase;

import com.worknector.offizz.openapi.tour.application.mapper.CafeMapper;
import com.worknector.offizz.domain.work.domain.entity.Cafe;
import com.worknector.offizz.domain.work.domain.repository.CafeRepository;
import com.worknector.offizz.openapi.tour.application.dto.AccommodationResponse;
import com.worknector.offizz.openapi.tour.application.dto.CafeResponse;
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
    private static final int NUM_OF_ROWS = 100;
    private static final int PAGE_NO = 1;

    private final TourOpenApiUseCase tourOpenApiUseCase;
    private final AccommodationRepository accommodationRepository;
    private final CafeRepository cafeRepository;

    public void updateAccommodationData() {
        int pageNo = PAGE_NO;
        List<Accommodation> accommodationEntityList = new ArrayList<>();

        while (true) {
            AccommodationResponse accommodationResponse = tourOpenApiUseCase.fetchAccommodationData(pageNo, NUM_OF_ROWS);

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

            int lastPage = (int) Math.ceil((double) totalCount / NUM_OF_ROWS);
            if (pageNo >= lastPage) {
                break;
            }

            pageNo++;
        }

        if (!accommodationEntityList.isEmpty()) {
            accommodationRepository.saveAll(accommodationEntityList);
        }
    }

    public void updateCafeData() {
        int pageNo = PAGE_NO;
        List<Cafe> cafes = new ArrayList<>();

        while (true) {
            CafeResponse cafeResponse = tourOpenApiUseCase.fetchCafeData(pageNo, NUM_OF_ROWS);

            CafeResponse.Items items = cafeResponse.response().body().items();
            int currentRows = cafeResponse.response().body().numOfRows();
            int totalCount = cafeResponse.response().body().totalCount();

            if (items == null || currentRows == 0) {
                break;
            }

            List<CafeResponse.Item> cafeItems = items.item();
            if (cafeItems.isEmpty()) {
                break;
            }

            for (CafeResponse.Item item : cafeItems) {
                Optional<Cafe> existingCafe = cafeRepository.findByContentId(item.contentid());

                if (existingCafe.isPresent()) {
                    // TODO: 기존 데이터 업데이트 로직 추가
                    continue;
                }

                // 새 데이터 삽입
                Cafe cafeEntity = CafeMapper.responseToCafe(item);
                cafes.add(cafeEntity);
            }

            int lastPage = (int) Math.ceil((double) totalCount / NUM_OF_ROWS);
            if (pageNo >= lastPage) {
                break;
            }

            pageNo++;
        }

        if (!cafes.isEmpty()) {
            cafeRepository.saveAll(cafes);
        }
    }
}
