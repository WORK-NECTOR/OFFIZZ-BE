package com.worknector.offizz.domain.work.domain.service;

import com.worknector.offizz.domain.work.domain.entity.Cafe;
import com.worknector.offizz.domain.work.domain.entity.Office;
import com.worknector.offizz.domain.work.domain.repository.CafeRepository;
import com.worknector.offizz.domain.work.domain.repository.OfficeRepository;
import com.worknector.offizz.domain.work.presenation.constant.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkGetService {
    private final OfficeRepository officeRepository;
    private final CafeRepository cafeRepository;

    public List<Cafe> allCafeSearchOrLocationPage(String search, double lat, double lon) {
        return cafeRepository.findAllPagingBySearchOrLocation(search, lat, lon);
    }
    public List<Office> recommendOffice(Region region, int size) {
        return officeRepository.findRecommendByRegion(region, size);
    }

    public Office officeById(long officeId) {
        Office office = officeRepository.findById(officeId).orElseThrow();
        office.updateHit();
        return office;
    }

    public Page<Office> allOfficeRegionOfficePage(Region region, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return officeRepository.findAllPagingByRegion(region, pageable);
    }

    public Page<Office> allOfficeSearchOfficePage(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return officeRepository.findAllPagingBySearch(search, pageable);
    }

    public List<Office> allOfficeSearchOrLocationPage(String search, double lat, double lon) {
        return officeRepository.findAllPagingBySearchOrLocation(search, lat, lon);
    }

}
