package com.worknector.offizz.domain.vacation.recommend.presentation;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.vacation.recommend.application.dto.res.PagingVacationRecommendResponse;
import com.worknector.offizz.domain.vacation.recommend.application.dto.res.VacationRecommendDetailResponse;
import com.worknector.offizz.domain.vacation.recommend.application.usecase.VacationRecommendUseCase;
import com.worknector.offizz.domain.vacation.recommend.presentation.constant.Filter;
import com.worknector.offizz.domain.vacation.recommend.presentation.constant.VacationCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vacation")
@Tag(name = "Vacation Recommend Controller")
public class VacationRecommendController {

    private final VacationRecommendUseCase vacationRecommendUseCase;

    @GetMapping("/{filter}/location/{page}/{size}")
    @Operation(summary = "VACATION > 추천) 검색에 따른 vacation 페이지",
            description = "'서울 강남' 검색 -> 서울과 강남을 모두 포함하는 주소 혹은 자연/맛집/문화예술/쇼핑 이름")
    public ResponseEntity<PagingVacationRecommendResponse> searchRecommendVacation(
            @AuthenticationPrincipal User user,
            @Schema(description = "vacation 필터: culture, nature, restaurant, shopping, all")
            @PathVariable Filter filter,
            @RequestParam(name = "search") @Nullable String search,
            @RequestParam(name = "lat") double lat,
            @RequestParam(name = "lon") double lon,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size") int size) {
        PagingVacationRecommendResponse recommendVacations = vacationRecommendUseCase.getRecommendVacation(filter, search, lat, lon, user, page, size);
        return ResponseEntity.ok(recommendVacations);
    }

    @GetMapping("/{category}/{id}")
    @Operation(summary = "VACATION > 추천) 검색에 따른 vacation 상세 페이지",
            description = "vacation 상세조회 페이지")
    public ResponseEntity<VacationRecommendDetailResponse> searchRecommendVacation(
            @Schema(description = "vacation 필터: culture, course, nature, restaurant, shopping")
            @PathVariable VacationCategory category,
            @Schema(description = "카테고리 별 id (cultureId, courseId, natureId, restaurantId, shoppingId")
            @PathVariable(name = "id") int id
    ) {
        VacationRecommendDetailResponse recommendVacationDetail = vacationRecommendUseCase.getRecommendVacationDetail(category, id);
        return ResponseEntity.ok(recommendVacationDetail);
    }
}
