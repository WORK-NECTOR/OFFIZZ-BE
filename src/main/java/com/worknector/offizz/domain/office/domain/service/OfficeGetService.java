package com.worknector.offizz.domain.office.domain.service;

import com.worknector.offizz.domain.office.domain.entity.Office;
import com.worknector.offizz.domain.office.domain.repository.OfficeRepository;
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
    private static final int PAGE_SIZE = 8;


    public List<Office> recommendOffice(String region) {
        return officeRepository.findRecommendByRegion(region);
    }

    public Office officeById(long officeId) {
        Office office = officeRepository.findById(officeId).orElseThrow();
        office.updateHit();
        return office;
    }

    public Page<Office> allOfficePage(String region, Integer page) {
        if (page == null)
            page = 1;
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return officeRepository.findAllPagingByRegion(region, pageable);
    }
}
