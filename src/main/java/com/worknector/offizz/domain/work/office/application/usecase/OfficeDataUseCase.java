package com.worknector.offizz.domain.work.office.application.usecase;

import com.worknector.offizz.domain.work.office.application.dto.req.Region;
import com.worknector.offizz.domain.work.office.application.dto.res.*;
import com.worknector.offizz.domain.work.office.application.mapper.OfficeMapper;
import com.worknector.offizz.domain.work.office.domain.entity.Office;
import com.worknector.offizz.domain.work.office.domain.service.OfficeGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class OfficeDataUseCase {
    private final OfficeGetService officeGetService;

    public RecOfficeResponse getRecommendOffice(Region region, int size) {
        List<Office> offices = officeGetService.recommendOffice(region, size);
        List<RecOffice> recOffices = offices.stream()
                .map(OfficeMapper::mapToRecOffice)
                .toList();
        return new RecOfficeResponse(recOffices);
    }

    public OfficeDetailResponse getOfficeDetail(long officeId) {
        Office office = officeGetService.officeById(officeId);
        return OfficeMapper.mapToOfficeDetail(office);
    }

    public PagingRecOfficeResponse getAllRecommendOffice(Region region, int page, int size) {
        Page<Office> offices = officeGetService.allRegionOfficePage(region, page, size);
        List<RecOffice> recOffices = offices.stream()
                .map(OfficeMapper::mapToRecOffice)
                .toList();
        return new PagingRecOfficeResponse(recOffices, offices.getTotalPages());
    }

    public PagingRecOfficeResponse getAllSearchOffice(String search, int page, int size) {
        Page<Office> offices = officeGetService.allSearchOfficePage(search, page, size);
        List<RecOffice> recOffices = offices.stream()
                .map(OfficeMapper::mapToRecOffice)
                .toList();
        return new PagingRecOfficeResponse(recOffices, offices.getTotalPages());
    }

    public PagingOfficeWithLatAndLonResponse getAllSearchOrLocation(String search, int page, int size, double lat, double lon) {
        Page<Office> offices = officeGetService.allSearchOrLocationPage(search, page, size, lat, lon);
        List<OfficeWithLatAndLon> officeWithLatAndLons = offices.stream()
                .map(OfficeMapper::mapToOfficeWithLatAndLon)
                .toList();
        return new PagingOfficeWithLatAndLonResponse(officeWithLatAndLons, offices.getTotalPages());
    }
}
