package com.worknector.offizz.domain.work.office.domain.service;

import com.worknector.offizz.domain.work.office.application.dto.req.Region;
import com.worknector.offizz.domain.work.office.domain.entity.Office;
import com.worknector.offizz.domain.work.office.domain.repository.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OfficeGetService {
    private final OfficeRepository officeRepository;

    public List<Office> recommendOffice(Region region, int size) {
        return officeRepository.findRecommendByRegion(region, size);
    }

    public Office officeById(long officeId) {
        Office office = officeRepository.findById(officeId).orElseThrow();
        office.updateHit();
        return office;
    }

    public Page<Office> allRegionOfficePage(Region region, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return officeRepository.findAllPagingByRegion(region, pageable);
    }

    public Page<Office> allSearchOfficePage(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return officeRepository.findAllPagingBySearch(search, pageable);
    }

}
