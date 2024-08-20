package com.worknector.offizz.domain.work.office.presentation;

import com.worknector.offizz.domain.work.office.application.dto.req.Region;
import com.worknector.offizz.domain.work.office.application.dto.res.OfficeDetailResponse;
import com.worknector.offizz.domain.work.office.application.dto.res.PagingRecOfficeResponse;
import com.worknector.offizz.domain.work.office.application.dto.res.RecOfficeResponse;
import com.worknector.offizz.domain.work.office.application.usecase.OfficeDataUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/office")
@Tag(name = "OFFICE Controller")
public class OfficeController {
    private final OfficeDataUseCase officeDataUseCase;

    @GetMapping("/rec/{region}/{size}")
    @Operation(summary = "장소에 따른 오피스 4개 추천 (장소 필수)", description = "서울 / 인천 / 경기 / 대구 / 대전 / 충청 / 경상 / 부산 / 기타")
    public ResponseEntity<RecOfficeResponse> recommendOffice(@PathVariable Region region, @PathVariable int size) {
        RecOfficeResponse recommendOffice = officeDataUseCase.getRecommendOffice(region, size);
        return ResponseEntity.ok(recommendOffice);
    }

    @GetMapping("/detail/{officeId}")
    @Operation(summary = "공유오피스 상세보기", description = "heating : 난방기 / parking : 주챠가능 / publicLounge : 공용라운지 / sharedKitchen : 공용주방 / " +
            "waterPurifier : 정수기 / terraceRooftop : 테라스/루프탑 / snacksDrinks : 음료(다과) / personalLocker : 개인락커 / tvProjector : 빔프로젝터 / whiteboard : 화이트보드 / " +
            "internetWifi : 와이파이 / showerFacility : 샤워시설 / storage : 창고 / doorLock : 도어락 / powerOutlet : 콘센트 / fax : 팩스 / twentyFourHoursOperation : 24시간 / " +
            "openAllYear : 연중무휴 / airConditioning : 에어컨 / cafeRestaurant : 카페/레스토랑 / copierPrinter : 복사(인쇄기)")
    public ResponseEntity<OfficeDetailResponse> officeDetail(@PathVariable long officeId) {
        OfficeDetailResponse officeDetail = officeDataUseCase.getOfficeDetail(officeId);
        return ResponseEntity.ok(officeDetail);
    }

    @GetMapping("/rec/all/{region}/{page}/{size}")
    @Operation(summary = "장소에 따른 오피스 페이지 - 8개씩 (장소 및 페이지 필수)", description = "서울 / 인천 / 경기 / 대구 / 대전 / 충청 / 경상 / 부산 / 기타")
    public ResponseEntity<PagingRecOfficeResponse> pagingRecommendOffice(@PathVariable int page, @PathVariable Region region, @PathVariable int size) {
        PagingRecOfficeResponse allRecommendOffice = officeDataUseCase.getAllRecommendOffice(region, page, size);
        return ResponseEntity.ok(allRecommendOffice);
    }

    @GetMapping("/search/{page}/{size}")
    @Operation(summary = "검색에 따른 오피스 페이지 - 8개씩", description = "'서울 강남' 검색 -> 서울과 강남을 모두 포함하는 주소 혹은 오피스 이름")
    public ResponseEntity<PagingRecOfficeResponse> searchOffice(@RequestParam String search, @PathVariable int page, @PathVariable int size) {
        PagingRecOfficeResponse allSearchOffice = officeDataUseCase.getAllSearchOffice(search, page, size);
        return ResponseEntity.ok(allSearchOffice);
    }
}
