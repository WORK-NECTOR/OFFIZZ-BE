package com.worknector.offizz.openapi.tour.application.usecase;

import com.worknector.offizz.domain.vacation.shopping.domain.entity.Shopping;
import com.worknector.offizz.domain.vacation.shopping.domain.repository.ShoppingRepository;
import com.worknector.offizz.openapi.tour.application.dto.TourOpenDataResponse;
import com.worknector.offizz.openapi.tour.application.mapper.ShoppingDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ShoppingOpenDataUseCase {

    private final TourOpenApiUseCase tourOpenApiUseCase;
    private final ShoppingRepository shoppingRepository;

    private static final int NUM_OF_ROWS = 100;

    public void updateShoppingData() {
        int pageNo = 1;
        List<Shopping> shoppingEntityList = new ArrayList<>();
        boolean continueFlag = true;

        while (continueFlag) {
            TourOpenDataResponse tourOpenDataResponse = tourOpenApiUseCase.fetchShoppingData(pageNo, NUM_OF_ROWS);

            TourOpenDataResponse.Items items = tourOpenDataResponse.response().body().items();
            int currentRows = tourOpenDataResponse.response().body().numOfRows();
            int totalCount = tourOpenDataResponse.response().body().totalCount();

            if (items == null || currentRows == 0) {
                break;
            }

            List<TourOpenDataResponse.Item> shoppingItemList = items.item();
            if (shoppingItemList.isEmpty()) {
                break;
            }

            for (TourOpenDataResponse.Item item : shoppingItemList) {
                Optional<Shopping> existingShopping = shoppingRepository.findByContentidAndTitle(item.contentid(), item.title());

                if (existingShopping.isPresent()) {
                    // TODO: 기존 데이터 업데이트 로직 추가
                    continue;
                }

                // 새 데이터 삽입
                Shopping shoppingEntity = ShoppingDataMapper.toEntity(item);
                shoppingEntityList.add(shoppingEntity);
            }

            int lastPage = (int) Math.ceil((double) totalCount / NUM_OF_ROWS);
            if (pageNo >= lastPage) {
                continueFlag = false;
            }

            pageNo++;
        }

        if (!shoppingEntityList.isEmpty()) {
            shoppingRepository.saveAll(shoppingEntityList);
        }
    }
}
