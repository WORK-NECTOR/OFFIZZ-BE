package com.worknector.offizz.domain.vacation.recommend.domain.service;

import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.likes.domain.entity.LikesCategory;
import com.worknector.offizz.domain.vacation.culture.domain.entity.Culture;
import com.worknector.offizz.domain.vacation.culture.domain.repository.CultureRepository;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Nature;
import com.worknector.offizz.domain.vacation.nature.domain.repository.CourseRepository;
import com.worknector.offizz.domain.vacation.nature.domain.repository.NatureRepository;
import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;
import com.worknector.offizz.domain.vacation.restaurant.domain.repository.RestaurantRepository;
import com.worknector.offizz.domain.vacation.shopping.domain.entity.Shopping;
import com.worknector.offizz.domain.vacation.shopping.domain.repository.ShoppingRepository;
import com.worknector.offizz.domain.workation.domain.entity.VacationKeyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacationGetService {
    // factory와 strategy로 이루어져있는데, 원하는 내용을 어디에 추가해야할지 모르겠어서, 일단 만들어서 사용

    private final NatureRepository natureRepository;
    private final CourseRepository courseRepository;
    private final RestaurantRepository restaurantRepository;
    private final CultureRepository cultureRepository;
    private final ShoppingRepository shoppingRepository;
    // todo : shopping 추가

    public String recommendVacation(VacationKeyword keyword) { //todo : 추천 방식 확정시 수정
        if (keyword.equals(VacationKeyword.CULTURE)) {
            List<Culture> cultures = cultureRepository.findAll();
            Collections.shuffle(cultures);
            return cultures.stream()
                    .findFirst()
                    .orElseThrow()
                    .getTitle();
        }
        if (keyword.equals(VacationKeyword.NATURE)) {
            List<Nature> natures = natureRepository.findAll();
            List<String> titles = natures.stream()
                    .map(Nature::getTitle)
                    .toList();
            courseRepository.findAll()
                    .forEach(course -> titles.add(course.getCrsKorNm()));
            Collections.shuffle(titles);
            return titles.stream()
                    .findFirst()
                    .orElseThrow();
        }
        if (keyword.equals(VacationKeyword.SHOPPING)) {
            List<Shopping> shoppings = shoppingRepository.findAll();
            Collections.shuffle(shoppings);
            return shoppings.stream()
                    .findFirst()
                    .orElseThrow()
                    .getTitle();
        }
        if (keyword.equals(VacationKeyword.RESTAURANT)) {
            List<Restaurant> restaurants = restaurantRepository.findAll();
            Collections.shuffle(restaurants);
            return restaurants.stream()
                    .findFirst()
                    .orElseThrow()
                    .getTitle();
        }
        return null;
    }

    public List<String> likeVacation(List<Likes> likes) {
        List<Likes> natureLikes = likes.stream()
                .filter(like -> like.getLikesCategory().equals(LikesCategory.nature))
                .toList();
        List<Likes> courseLikes = likes.stream()
                .filter(like -> like.getLikesCategory().equals(LikesCategory.course))
                .toList();
        List<Likes> cultureLikes = likes.stream()
                .filter(like -> like.getLikesCategory().equals(LikesCategory.culture))
                .toList();
        List<Likes> restaurantLikes = likes.stream()
                .filter(like -> like.getLikesCategory().equals(LikesCategory.restaurant))
                .toList();
        List<Likes> shoppingLikes = likes.stream()
                .filter(like -> like.getLikesCategory().equals(LikesCategory.shopping))
                .toList();

        List<String> vacationNames = new ArrayList<>();

        natureRepository.findAllNatureId(natureLikes)
                .forEach(nature -> vacationNames.add(nature.getTitle()));
        courseRepository.findAllCourseId(courseLikes)
                .forEach(course -> vacationNames.add(course.getCrsKorNm()));
        cultureRepository.findAllCourseId(cultureLikes)
                .forEach(culture -> vacationNames.add(culture.getTitle()));
        restaurantRepository.findAllRestaurantById(restaurantLikes)
                .forEach(restaurant -> vacationNames.add(restaurant.getTitle()));
        shoppingRepository.findAllShoppingById(shoppingLikes)
                .forEach(shopping -> vacationNames.add(shopping.getTitle()));
        return vacationNames;
    }
}
