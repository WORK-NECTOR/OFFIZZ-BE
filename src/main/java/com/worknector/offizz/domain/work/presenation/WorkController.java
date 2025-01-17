package com.worknector.offizz.domain.work.presenation;

import com.worknector.offizz.domain.likes.application.dto.req.Like;
import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.work.application.usecase.WorkDataUseCase;
import com.worknector.offizz.domain.work.application.dto.res.PagingCafeAndOffice;
import com.worknector.offizz.domain.work.presenation.constant.Region;
import com.worknector.offizz.domain.work.application.dto.res.OfficeDetailResponse;
import com.worknector.offizz.domain.work.application.dto.res.PagingRecOfficeResponse;
import com.worknector.offizz.domain.work.application.dto.res.RecOfficeResponse;
import com.worknector.offizz.domain.work.presenation.constant.Filter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/work/")
@Tag(name = "WORK CONTROLLER")
public class WorkController {
    private final WorkDataUseCase workDataUseCase;

    @GetMapping("/office/rec/{region}/{size}")
    @Operation(summary = "장소에 따른 오피스 4개 추천 (장소 필수)", description = "수도권, 경상권, 전라권, 강원권, 제주권, 충청권")
    public ResponseEntity<RecOfficeResponse> recommendOffice(@PathVariable Region region, @PathVariable int size) {
        RecOfficeResponse recommendOffice = workDataUseCase.getRecommendOffice(region, size);
        return ResponseEntity.ok(recommendOffice);
    }

    @GetMapping("/office/detail/{officeId}")
    @Operation(summary = "공유오피스 상세보기", description = "heating : 난방기 / parking : 주챠가능 / publicLounge : 공용라운지 / sharedKitchen : 공용주방 / " +
            "waterPurifier : 정수기 / terraceRooftop : 테라스/루프탑 / snacksDrinks : 음료(다과) / personalLocker : 개인락커 / tvProjector : 빔프로젝터 / whiteboard : 화이트보드 / " +
            "internetWifi : 와이파이 / showerFacility : 샤워시설 / storage : 창고 / doorLock : 도어락 / powerOutlet : 콘센트 / fax : 팩스 / twentyFourHoursOperation : 24시간 / " +
            "openAllYear : 연중무휴 / airConditioning : 에어컨 / cafeRestaurant : 카페/레스토랑 / copierPrinter : 복사(인쇄기)")
    public ResponseEntity<OfficeDetailResponse> officeDetail(@PathVariable long officeId) {
        OfficeDetailResponse officeDetail = workDataUseCase.getOfficeDetail(officeId);
        return ResponseEntity.ok(officeDetail);
    }

    @GetMapping("/office/rec/all/{region}/{page}/{size}")
    @Operation(summary = "장소에 따른 오피스 페이지 - 8개씩 (장소 및 페이지 필수)", description = "수도권, 경상권, 전라권, 강원권, 제주권, 충청권")
    public ResponseEntity<PagingRecOfficeResponse> pagingRecommendOffice(@PathVariable int page, @PathVariable Region region, @PathVariable int size) {
        PagingRecOfficeResponse allRecommendOffice = workDataUseCase.getAllRecommendOffice(region, page, size);
        return ResponseEntity.ok(allRecommendOffice);
    }

    @GetMapping("/office/search/{page}/{size}")
    @Operation(summary = "검색에 따른 오피스 페이지 - 8개씩", description = "'서울 강남' 검색 -> 서울과 강남을 모두 포함하는 주소 혹은 오피스 이름")
    public ResponseEntity<PagingRecOfficeResponse> searchOffice(@RequestParam String search, @PathVariable int page, @PathVariable int size) {
        PagingRecOfficeResponse allSearchOffice = workDataUseCase.getAllSearchOffice(search, page, size);
        return ResponseEntity.ok(allSearchOffice);
    }

    @GetMapping("/{filter}/location/{page}/{size}")
    @Operation(summary = "위치 및 검색 따른 오피스 페이지 - 8개씩", description = "lat에 위도, lon에 경도(필수) + '서울 강남' 검색(선택) -> 서울과 강남을 모두 포함하는 주소 혹은 오피스 이름")
    public ResponseEntity<PagingCafeAndOffice> searchOffice(@AuthenticationPrincipal User user,
                                                            @PathVariable Filter filter,
                                                            @PathVariable(name = "page") int page,
                                                            @PathVariable(name = "size") int size,
                                                            @RequestParam(name = "search") @Nullable String search,
                                                            @RequestParam(name = "lat") double lat,
                                                            @RequestParam(name = "lon") double lon) {
        PagingCafeAndOffice a = workDataUseCase.getAllSearchOrLocation(filter, search, page, size, lat, lon, user);
        return ResponseEntity.ok(a);
    }
}
