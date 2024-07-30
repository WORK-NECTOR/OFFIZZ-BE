package com.worknector.offizz.domain.office.presentation;

import com.worknector.offizz.domain.office.application.dto.req.Region;
import com.worknector.offizz.domain.office.application.dto.res.OfficeDetailResponse;
import com.worknector.offizz.domain.office.application.dto.res.PagingRecOfficeResponse;
import com.worknector.offizz.domain.office.application.dto.res.RecOfficeResponse;
import com.worknector.offizz.domain.office.application.usecase.OfficeDataUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/office")
@Tag(name = "OFFICE Controller")
public class OfficeController {
    private final OfficeDataUseCase officeDataUseCase;

    @GetMapping("/rec/{region}")
    public ResponseEntity<RecOfficeResponse> recommendOffice(@PathVariable Region region) {
        RecOfficeResponse recommendOffice = officeDataUseCase.getRecommendOffice(region);
        return ResponseEntity.ok(recommendOffice);
    }

    @GetMapping("/detail/{officeId}")
    public ResponseEntity<OfficeDetailResponse> officeDetail(@PathVariable long officeId) {
        OfficeDetailResponse officeDetail = officeDataUseCase.getOfficeDetail(officeId);
        return ResponseEntity.ok(officeDetail);
    }

    @GetMapping("/rec/all/{region}/{page}")
    public ResponseEntity<PagingRecOfficeResponse> pagingRecommendOffice(@PathVariable Integer page, @PathVariable Region region) {
        PagingRecOfficeResponse allRecommendOffice = officeDataUseCase.getAllRecommendOffice(region, page);
        return ResponseEntity.ok(allRecommendOffice);
    }
}
