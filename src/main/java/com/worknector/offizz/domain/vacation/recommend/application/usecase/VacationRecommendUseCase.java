package com.worknector.offizz.domain.vacation.recommend.application.usecase;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.vacation.culture.domain.entity.Culture;
import com.worknector.offizz.domain.vacation.culture.domain.service.CultureGetService;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Course;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Nature;
import com.worknector.offizz.domain.vacation.nature.domain.service.CourseGetService;
import com.worknector.offizz.domain.vacation.nature.domain.service.NatureGetService;
import com.worknector.offizz.domain.vacation.recommend.application.dto.res.PagingVacationRecommendResponse;
import com.worknector.offizz.domain.vacation.recommend.application.dto.res.VacationRecommendDetailResponse;
import com.worknector.offizz.domain.vacation.recommend.application.dto.res.VacationRecommendResponse;
import com.worknector.offizz.domain.vacation.recommend.application.mapper.VacationRecommendMapper;
import com.worknector.offizz.domain.vacation.recommend.domain.factory.VacationRecommendFactory;
import com.worknector.offizz.domain.vacation.recommend.domain.strategy.VacationRecommendStrategy;
import com.worknector.offizz.domain.vacation.recommend.exception.InvalidVacationCategoryException;
import com.worknector.offizz.domain.vacation.recommend.presentation.constant.Filter;
import com.worknector.offizz.domain.vacation.recommend.presentation.constant.VacationCategory;
import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;
import com.worknector.offizz.domain.vacation.restaurant.domain.service.RestaurantGetService;
import com.worknector.offizz.domain.vacation.shopping.domain.entity.Shopping;
import com.worknector.offizz.domain.vacation.shopping.domain.service.ShoppingGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.worknector.offizz.global.util.HaversineUtils.distanceForSort;

@RequiredArgsConstructor
@Service
public class VacationRecommendUseCase {
    private final VacationRecommendFactory vacationRecommendFactory;
    private final CultureGetService cultureGetService;
    private final RestaurantGetService restaurantGetService;
    private final NatureGetService natureGetService;
    private final CourseGetService courseGetService;
    private final ShoppingGetService shoppingGetService;

    /**
     * Vacation 추천
     * 필터와 검색어 기반으로 조회하여, 거리 순 정렬 후, 페이징한 뒤 반환
     *
     * @param filter  사용자 설정에 따른 Vacation 필터
     * @param search   검색어
     * @param lat      사용자의 현 위도
     * @param lon      사용자의 현 경도
     * @param page     요청 페이지 번호
     * @param size     한 페이지 당 개수
     * @return  페이징된 추천 vacation 리스트와 총 페이지 수 반환
     */
    public PagingVacationRecommendResponse getRecommendVacation(Filter filter, String search, double lat, double lon, User user, int page, int size) {
        List<VacationRecommendResponse> allRecommendations = new ArrayList<>(getAllRecommendations(filter, search, lat, lon, user.getUserId()));

        allRecommendations.sort(Comparator.comparingDouble(o -> distanceForSort(lat, lon, o.lat(), o.lon())));

        PageImpl<VacationRecommendResponse> responses = pagingAllRecommendations(page, size, allRecommendations);

        return new PagingVacationRecommendResponse(responses.getContent(), responses.getTotalPages());
    }

    public VacationRecommendDetailResponse getRecommendVacationDetail(VacationCategory category, int id) {
        switch (category) {
            case culture:
                Culture culture = cultureGetService.findCultureById(id);
                return VacationRecommendMapper.fromCulture(culture);
            case nature:
                Nature nature = natureGetService.findNatureById(id);
                return VacationRecommendMapper.fromNature(nature);
            case course:
                Course course = courseGetService.findCourseById(id);
                return VacationRecommendMapper.fromCourse(course);
            case restaurant:
                Restaurant restaurant = restaurantGetService.findRestaurantById(id);
                return VacationRecommendMapper.fromRestaurant(restaurant);
            case shopping:
                Shopping shopping = shoppingGetService.findShoppingById(id);
                return VacationRecommendMapper.fromShopping(shopping);
            default:
                throw new InvalidVacationCategoryException();
        }
    }

    private List<VacationRecommendResponse> getAllRecommendations(Filter filter, String search, double lat, double lon, Long userId) {
        VacationRecommendStrategy strategy = vacationRecommendFactory.getRecommendationStrategy(filter);
        List<VacationRecommendResponse> recommendations = strategy.recommend(search, lat, lon, userId);

        return recommendations;
    }

    private static PageImpl<VacationRecommendResponse> pagingAllRecommendations(int page, int size, List<VacationRecommendResponse> allRecommendations) {
        Pageable pageable = PageRequest.of(page - 1, size);
        int start = Math.min((page - 1) * size, allRecommendations.size());
        int end = Math.min(((page - 1) * size) + size, allRecommendations.size());

        List<VacationRecommendResponse> currentPageResponses = allRecommendations.subList(start, end);
        PageImpl<VacationRecommendResponse> pagedResponses = new PageImpl<>(currentPageResponses, pageable, allRecommendations.size());
        return pagedResponses;
    }
}
