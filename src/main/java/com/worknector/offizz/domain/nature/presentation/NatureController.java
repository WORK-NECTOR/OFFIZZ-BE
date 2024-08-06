package com.worknector.offizz.domain.nature.presentation;

import com.worknector.offizz.domain.nature.application.dto.res.RecommendNaturePagingResponse;
import com.worknector.offizz.domain.nature.application.usecase.RecommendNatureUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nature")
@Tag(name = "NATURE Controller")
public class NatureController {
    private final RecommendNatureUseCase recommendNatureUseCase;

    @GetMapping("/recommend")
    @Operation(summary = "VACATION > 큐레이션) 검색에 따른 자연 페이지", description = "'서울 강남' 검색 -> 서울과 강남을 모두 포함하는 주소 혹은 자연 관광지 이름")
    public ResponseEntity<RecommendNaturePagingResponse> searchRecommendNature(@RequestParam(name = "search") String search,
                                                                               @RequestParam(name = "page") int page,
                                                                               @RequestParam(name = "size") int size)
    {
        RecommendNaturePagingResponse allSearchNature = recommendNatureUseCase.searchRecommendNature(search, page, size);
        return ResponseEntity.ok(allSearchNature);
    }
}
