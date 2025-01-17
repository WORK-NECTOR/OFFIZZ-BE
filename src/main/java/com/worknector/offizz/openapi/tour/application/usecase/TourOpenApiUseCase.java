package com.worknector.offizz.openapi.tour.application.usecase;

import com.worknector.offizz.openapi.tour.application.dto.AccommodationResponse;
import com.worknector.offizz.openapi.tour.application.dto.TourOpenDataResponse;
import com.worknector.offizz.openapi.tour.application.dto.CafeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
@RequiredArgsConstructor
public class TourOpenApiUseCase {
    private final WebClient webClient;

    @Value("${open-api.tour.base-url}")
    private String baseUrl;

    @Value("${open-api.tour.url-path.accommodation}")
    private String accommodationUrlPath;

    @Value("${open-api.tour.url-path.area-based-list}")
    private String areaBasedListUrlPath;

    @Value("${open-api.tour.url-path.cafe}")
    private String cafeUrlPath;

    @Value("${open-api.tour.cafe-code}")
    private String cafeCode;

    @Value("${open-api.mobile-os}")
    private String mobileOS;

    @Value("${open-api.mobile-app}")
    private String mobileApp;

    @Value("${open-api.service-key}")
    private String serviceKey;

    public AccommodationResponse fetchAccommodationData(int pageNo, int numOfRows) {
        return callOpenApiAndGetResponse(pageNo, numOfRows, accommodationUrlPath, AccommodationResponse.class, null, null, null);
    }

    public TourOpenDataResponse fetchAreaBasedListNatureData(int pageNo, int numOfRows) {
        return callOpenApiAndGetResponse(pageNo, numOfRows, areaBasedListUrlPath, TourOpenDataResponse.class, "12", "A01", null);
    }

    public CafeResponse fetchCafeData(int pageNo, int numOfRows) {
        return callOpenApiAndGetResponse(pageNo, numOfRows, cafeUrlPath, CafeResponse.class, null, null, cafeCode);
    }

    public TourOpenDataResponse fetchRestaurantData(int pageNo, int numOfRows, String cat3) {
        return callOpenApiAndGetResponse(pageNo, numOfRows, areaBasedListUrlPath, TourOpenDataResponse.class, "39", null, cat3);
    }

    public TourOpenDataResponse fetchCultureData(int pageNo, int numOfRows) {
        return callOpenApiAndGetResponse(pageNo, numOfRows, areaBasedListUrlPath, TourOpenDataResponse.class, "14", null, null);
    }

    public TourOpenDataResponse fetchShoppingData(int pageNo, int numOfRows) {
        return callOpenApiAndGetResponse(pageNo, numOfRows, areaBasedListUrlPath, TourOpenDataResponse.class, "38", null, null);
    }

    private <T> T callOpenApiAndGetResponse(int pageNo, int numOfRows, String urlPath, Class<T> responseType, String contentTypeId, String cat1, String cat3) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        return webClient.mutate()
            .uriBuilderFactory(factory)
            .baseUrl(baseUrl)
            .build()
            .get()
            .uri(uriBuilder -> {
              uriBuilder.path(urlPath)
                      .queryParam("serviceKey", serviceKey)
                      .queryParam("_type", "json")
                      .queryParam("pageNo", pageNo)
                      .queryParam("numOfRows", numOfRows)
                      .queryParam("MobileOS", mobileOS)
                      .queryParam("MobileApp", mobileApp);

              if (contentTypeId != null) {
                uriBuilder.queryParam("contentTypeId", contentTypeId);
              }
              if (cat1 != null) {
                uriBuilder.queryParam("cat1", cat1);
              }
              if (cat3 != null) {
                uriBuilder.queryParam("cat3", cat3);
              }

              return uriBuilder.build();
            })
            .retrieve()
            .bodyToMono(responseType)
            .block();
    }
}
