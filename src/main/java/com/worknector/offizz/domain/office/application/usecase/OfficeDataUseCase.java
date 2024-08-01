package com.worknector.offizz.domain.office.application.usecase;

import com.worknector.offizz.domain.office.application.dto.req.Region;
import com.worknector.offizz.domain.office.application.dto.res.OfficeDetailResponse;
import com.worknector.offizz.domain.office.application.dto.res.PagingRecOfficeResponse;
import com.worknector.offizz.domain.office.application.dto.res.RecOffice;
import com.worknector.offizz.domain.office.application.dto.res.RecOfficeResponse;
import com.worknector.offizz.domain.office.application.mapper.OfficeMapper;
import com.worknector.offizz.domain.office.domain.entity.Office;
import com.worknector.offizz.domain.office.domain.service.OfficeGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.worknector.offizz.domain.office.application.mapper.OfficeMapper.mapToOfficeDetail;

@RequiredArgsConstructor
@Transactional
@Service
public class OfficeDataUseCase {
    private final OfficeGetService officeGetService;

    public RecOfficeResponse getRecommendOffice(Region region) {
        List<Office> offices = officeGetService.recommendOffice(region);
        List<RecOffice> recOffices = offices.stream()
                .map(OfficeMapper::mapToRecOffice)
                .toList();
        return new RecOfficeResponse(recOffices);
    }

    public OfficeDetailResponse getOfficeDetail(long officeId) {
        Office office = officeGetService.officeById(officeId);
        return mapToOfficeDetail(office);
    }

    public PagingRecOfficeResponse getAllRecommendOffice(Region region, int page) {
        Page<Office> offices = officeGetService.allRegionOfficePage(region, page);
        List<RecOffice> recOffices = offices.stream()
                .map(OfficeMapper::mapToRecOffice)
                .toList();
        return new PagingRecOfficeResponse(recOffices, offices.getTotalPages());
    }

    public PagingRecOfficeResponse getAllSearchOffice(String search, int page) {
        Page<Office> offices = officeGetService.allSearchOfficePage(search, page);
        List<RecOffice> recOffices = offices.stream()
                .map(OfficeMapper::mapToRecOffice)
                .toList();
        return new PagingRecOfficeResponse(recOffices, offices.getTotalPages());
    }
}
