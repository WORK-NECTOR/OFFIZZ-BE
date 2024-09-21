package com.worknector.offizz.domain.work.application.usecase;

import com.worknector.offizz.domain.likes.application.dto.req.Like;
import com.worknector.offizz.domain.likes.application.mapper.LikeMapper;
import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.service.LikesSaveService;
import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.work.domain.service.WorkGetService;
import com.worknector.offizz.domain.work.presenation.constant.Region;
import com.worknector.offizz.domain.work.application.dto.res.*;
import com.worknector.offizz.domain.work.application.mapper.WorkMapper;
import com.worknector.offizz.domain.work.presenation.constant.Filter;
import com.worknector.offizz.domain.work.domain.entity.Office;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.worknector.offizz.global.util.HaversineUtils.distanceForSort;

@RequiredArgsConstructor
@Service
@Transactional
public class WorkDataUseCase {
    private final WorkGetService workGetService;
    private final LikesSaveService likesSaveService;

    public void saveWorkLike(User user, Like workLike) {
        Likes likes = LikeMapper.mapToLikes(user, workLike);
        likesSaveService.save(likes);
    }

    public RecOfficeResponse getRecommendOffice(Region region, int size) {
        List<Office> offices = workGetService.recommendOffice(region, size);
        List<RecOffice> recOffices = offices.stream()
                .map(WorkMapper::mapToRecOffice)
                .toList();
        return new RecOfficeResponse(recOffices);
    }

    public OfficeDetailResponse getOfficeDetail(long officeId) {
        Office office = workGetService.officeById(officeId);
        return WorkMapper.mapToOfficeDetail(office);
    }

    public PagingRecOfficeResponse getAllRecommendOffice(Region region, int page, int size) {
        Page<Office> offices = workGetService.allOfficeRegionOfficePage(region, page, size);
        List<RecOffice> recOffices = offices.stream()
                .map(WorkMapper::mapToRecOffice)
                .toList();
        return new PagingRecOfficeResponse(recOffices, offices.getTotalPages());
    }

    public PagingRecOfficeResponse getAllSearchOffice(String search, int page, int size) {
        Page<Office> offices = workGetService.allOfficeSearchOfficePage(search, page, size);
        List<RecOffice> recOffices = offices.stream()
                .map(WorkMapper::mapToRecOffice)
                .toList();
        return new PagingRecOfficeResponse(recOffices, offices.getTotalPages());
    }

    public PagingCafeAndOffice getAllSearchOrLocation (Filter filter, String search, int page, int size, double lat, double lon, User user) {
        List<CafeAndOffice> cafeAndOffices = getCafeAndOffices(filter, search, lat, lon, user);
        cafeAndOffices.sort((o1, o2) -> {
            double first = distanceForSort(lat, lon, o1.lat(), o1.lon());
            double second = distanceForSort(lat, lon, o2.lat(), o2.lon());
            if (first > second)
                return 1;
            if (first == second)
                return 0;
            return -1;
        });

        return getPagingCafeAndOffice(page, size, cafeAndOffices);
    }

    private List<CafeAndOffice> getCafeAndOffices(Filter filter, String search, double lat, double lon, User user) {
        List<CafeAndOffice> cafeAndOffices = new ArrayList<>();
        List<SelectCafe> cafes = new ArrayList<>();
        List<SelectOffice> offices = new ArrayList<>();
        if (filter.equals(Filter.cafe) || filter.equals(Filter.all))
            cafes = workGetService.allCafeSearchOrLocationPage(search, lat, lon, user);
        if (filter.equals(Filter.office) || filter.equals(Filter.all))
            offices = workGetService.allOfficeSearchOrLocationPage(search, lat, lon, user);

        cafes.forEach(
                cafe -> cafeAndOffices.add(WorkMapper.mapToCafeAndOffice(cafe))
        );
        offices.forEach(
                office -> cafeAndOffices.add(WorkMapper.mapToCafeAndOffice(office))
        );
        return cafeAndOffices;
    }

    private static PagingCafeAndOffice getPagingCafeAndOffice(int page, int size, List<CafeAndOffice> cafeAndOffices) {
        Pageable pageable = PageRequest.of(page -1, size);
        int start = Math.min((page -1)* size, cafeAndOffices.size());
        int end = Math.min(((page -1)* size)+ size, cafeAndOffices.size());
        List<CafeAndOffice> subCafeAndOffices = cafeAndOffices.subList(start, end);
        Page<CafeAndOffice> cafeAndOfficePage = new PageImpl<>(subCafeAndOffices, pageable, cafeAndOffices.size());

        return new PagingCafeAndOffice(cafeAndOfficePage.getContent(), cafeAndOfficePage.getTotalPages());
    }
}
