package com.worknector.offizz.domain.vacation.restaurant.presentation;

import com.worknector.offizz.domain.vacation.restaurant.application.dto.PagingRestaurantRecommendList;
import com.worknector.offizz.domain.vacation.restaurant.application.usecase.RestaurantRecommendUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurant")
@Tag(name = "RESTAURANT Controller")
public class RestaurantRecommendController {
    private final RestaurantRecommendUseCase restaurantRecommendUseCase;

    @GetMapping("/search/{page}/{size}")
    @Operation(summary = "검색에 따른 음식점 페이지", description = "'서울 강남' 검색 -> 서울과 강남을 모두 포함하는 주소 혹은 음식점 이름")
    public ResponseEntity<PagingRestaurantRecommendList> searchRestaurant(@PathVariable(name = "page") int page,
                                                                          @PathVariable(name = "size") int size,
                                                                          @RequestParam(name = "search") String search,
                                                                          @RequestParam(name = "lat") double lat,
                                                                          @RequestParam(name = "lon") double lon) {
        PagingRestaurantRecommendList allSearchOffice = restaurantRecommendUseCase.searchRecommendRestaurant(search, page, size, lat, lon);
        return ResponseEntity.ok(allSearchOffice);
    }
}
