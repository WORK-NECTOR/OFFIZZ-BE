package com.worknector.offizz.openapi.tour.application.usecase;

import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;
import com.worknector.offizz.domain.vacation.restaurant.domain.repository.RestaurantRepository;
import com.worknector.offizz.openapi.tour.application.dto.TourOpenDataResponse;
import com.worknector.offizz.openapi.tour.application.mapper.RestaurantDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantOpenDataUseCase {

    private final TourOpenApiUseCase tourOpenApiUseCase;
    private final RestaurantRepository restaurantRepository;

    private static final int NUM_OF_ROWS = 100;

    public void updateRestaurantData(List<String> cat3List) {
        int pageNo = 1;
        List<Restaurant> restaurantEntityList = new ArrayList<>();
        boolean continueFlag = true;

        for (String cat3 : cat3List) {
            while (continueFlag) {
                TourOpenDataResponse tourOpenDataResponse = tourOpenApiUseCase.fetchRestaurantData(pageNo, NUM_OF_ROWS, cat3);

                TourOpenDataResponse.Items items = tourOpenDataResponse.response().body().items();
                int currentRows = tourOpenDataResponse.response().body().numOfRows();
                int totalCount = tourOpenDataResponse.response().body().totalCount();

                if (items == null || currentRows == 0) {
                    break;
                }

                List<TourOpenDataResponse.Item> restaurantItemList = items.item();
                if (restaurantItemList.isEmpty()) {
                    break;
                }

                for (TourOpenDataResponse.Item item : restaurantItemList) {
                    Optional<Restaurant> existingRestaurant = restaurantRepository.findByContentidAndTitle(item.contentid(), item.title());

                    if (existingRestaurant.isPresent()) {
                        // TODO: 기존 데이터 업데이트 로직 추가
                        continue;
                    }

                    // 새 데이터 삽입
                    Restaurant restaurantEntity = RestaurantDataMapper.toEntity(item);
                    restaurantEntityList.add(restaurantEntity);
                }

                int lastPage = (int) Math.ceil((double) totalCount / NUM_OF_ROWS);
                if (pageNo >= lastPage) {
                    continueFlag = false;
                }

                pageNo++;
            }

            if (!restaurantEntityList.isEmpty()) {
                restaurantRepository.saveAll(restaurantEntityList);
            }
        }
    }
}
