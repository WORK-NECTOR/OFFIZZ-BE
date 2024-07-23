package com.worknector.offizz.openapi.tour.application.usecase;

import com.worknector.offizz.openapi.tour.application.dto.AccommodationResponse;
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
  private String urlPath;

  @Value("${open-api.mobile-os}")
  private String mobileOS;

  @Value("${open-api.mobile-app}")
  private String mobileApp;

  @Value("${open-api.service-key}")
  private String serviceKey;

  public AccommodationResponse fetchAccommodationData(int pageNo, int numOfRows) {
    DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
    factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

    return webClient.mutate()
            .uriBuilderFactory(factory)
            .baseUrl(baseUrl)
            .build()
            .get()
            .uri(uriBuilder ->
                    uriBuilder
                            .path(urlPath)
                            .queryParam("serviceKey", serviceKey)
                            .queryParam("_type", "json")
                            .queryParam("pageNo", pageNo)
                            .queryParam("numOfRows", numOfRows)
                            .queryParam("MobileOS", mobileOS)
                            .queryParam("MobileApp", mobileApp)
                            .build()
            )
            .retrieve()
            .bodyToMono(AccommodationResponse.class)
            .block();
  }
}
