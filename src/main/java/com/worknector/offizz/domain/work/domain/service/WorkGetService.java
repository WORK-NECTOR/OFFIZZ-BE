package com.worknector.offizz.domain.work.domain.service;

import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.entity.LikesCategory;
import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.work.application.dto.res.SelectCafe;
import com.worknector.offizz.domain.work.application.dto.res.SelectOffice;
import com.worknector.offizz.domain.work.domain.entity.Cafe;
import com.worknector.offizz.domain.work.domain.entity.Office;
import com.worknector.offizz.domain.work.domain.repository.CafeRepository;
import com.worknector.offizz.domain.work.domain.repository.OfficeRepository;
import com.worknector.offizz.domain.work.presenation.constant.Region;
import com.worknector.offizz.domain.workation.domain.entity.WorkKeyword;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.worknector.offizz.domain.workation.domain.entity.WorkKeyword.CAFE;

@Service
@RequiredArgsConstructor
public class WorkGetService {
    private final OfficeRepository officeRepository;
    private final CafeRepository cafeRepository;

    public List<SelectCafe> allCafeSearchOrLocationPage(String search, double lat, double lon, User user) {
        return cafeRepository.findAllPagingBySearchOrLocation(search, lat, lon, user);
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

    public List<SelectOffice> allOfficeSearchOrLocationPage(String search, double lat, double lon, User user) {
        return officeRepository.findAllPagingBySearchOrLocation(search, lat, lon, user);
    }

    public List<String> likeWork(List<Likes> likes) {
        List<Likes> cafeLikes = likes.stream()
                .filter(like -> like.getLikesCategory().equals(LikesCategory.CAFE))
                .toList();
        List<Likes> officeLikes = likes.stream()
                .filter(like -> like.getLikesCategory().equals(LikesCategory.OFFICE))
                .toList();
        List<String> workNames = new ArrayList<>();
        cafeRepository.findAllCafeById(cafeLikes)
                .stream()
                .map(cafe -> workNames.add(cafe.getCafeName()));
        officeRepository.findAllOfficeById(officeLikes)
                .stream()
                .map(office -> workNames.add(office.getOfficeName()));
        return workNames;
    }

    public String recommendWork(WorkKeyword keyword) { //todo : 추천 방식 확정시 수정
        if (keyword.equals(WorkKeyword.OFFICE)) {
            List<Office> offices = officeRepository.findAll();
            Collections.shuffle(offices);
            return offices.stream()
                    .findFirst()
                    .orElseThrow()
                    .getOfficeName();
        }
        if (keyword.equals(CAFE)) {
            List<Cafe> cafes = cafeRepository.findAll();
            Collections.shuffle(cafes);
            return cafes.stream()
                    .findFirst()
                    .orElseThrow()
                    .getCafeName();
        }
        return null;
    }
}
