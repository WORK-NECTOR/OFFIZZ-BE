package com.worknector.offizz.domain.work.cafe.application.usecase;

import com.worknector.offizz.domain.work.cafe.application.dto.res.CafeWithLatAndLon;
import com.worknector.offizz.domain.work.cafe.application.dto.res.PagingCafeWithLatAndLonResponse;
import com.worknector.offizz.domain.work.cafe.application.mapper.CafeMapper;
import com.worknector.offizz.domain.work.cafe.domain.entity.Cafe;
import com.worknector.offizz.domain.work.cafe.domain.service.CafeGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class CafeDataUseCase {
    private final CafeGetService cafeGetService;

    @Transactional(readOnly = true)
    public PagingCafeWithLatAndLonResponse getAllSearchOrLocation(String search, int page, int size, double lat, double lon) {
        Page<Cafe> cafes = cafeGetService.allSearchOrLocationPage(search, page, size, lat, lon);
        List<CafeWithLatAndLon> cafeWithLatAndLons = cafes.stream()
                .map(CafeMapper::mapToCafeWithLatAndLon)
                .toList();
        return new PagingCafeWithLatAndLonResponse(cafeWithLatAndLons, cafes.getTotalPages());
    }
}
