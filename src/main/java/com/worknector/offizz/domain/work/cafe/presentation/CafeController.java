package com.worknector.offizz.domain.work.cafe.presentation;

import com.worknector.offizz.domain.work.cafe.application.dto.res.PagingCafeWithLatAndLonResponse;
import com.worknector.offizz.domain.work.cafe.application.usecase.CafeDataUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cafe")
@Tag(name = "CAFE CONTROLLER")
public class CafeController {
    private final CafeDataUseCase cafeDataUseCase;

    @GetMapping("/location/{page}/{size}")
    @Operation(summary = "위치 및 검색 따른 카페 페이지 - 8개씩", description = "lat에 위도, lon에 경도(필수) + '서울 강남' 검색(선택) -> 서울과 강남을 모두 포함하는 주소 혹은 카페 이름")
    public ResponseEntity<PagingCafeWithLatAndLonResponse> searchOffice(@PathVariable(name = "page") int page,
                                                                        @PathVariable(name = "size") int size,
                                                                        @RequestParam(name = "search") @Nullable String search,
                                                                        @RequestParam(name = "lat") double lat,
                                                                        @RequestParam(name = "lon") double lon) {
        PagingCafeWithLatAndLonResponse allSearchCafe = cafeDataUseCase.getAllSearchOrLocation(search, page, size, lat, lon);
        return ResponseEntity.ok(allSearchCafe);
    }
}
