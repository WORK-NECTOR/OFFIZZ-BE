package com.worknector.offizz.openapi.tour.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
@Transactional
@RequiredArgsConstructor
public class TourOpenApiUseCase {

  @Value("${open-api.tour.base-url}")
  private String baseUrl;

  @Value("${open-api.tour.url-path}")
  private String urlPath;

  @Value("${open-api.mobile-os}")
  private String mobileOS;

  @Value("${open-api.mobile-app}")
  private String mobileApp;

  @Value("${open-api.service-key}")
  private String serviceKey;

  public WebClient tourWebClient() {
    DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
    factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

    return WebClient.builder()
        .uriBuilderFactory(factory)
        .baseUrl(baseUrl)
        .build();
  }

  public String fetchTourData() {
      return tourWebClient()
        .get()
        .uri(uriBuilder ->
            uriBuilder
                .path(urlPath)
                .queryParam("serviceKey", serviceKey)
                .queryParam("_type", "json")
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 100)
                .queryParam("MobileOS", mobileOS)
                .queryParam("MobileApp", mobileApp)
                .build()
        )
        .retrieve()
        .bodyToMono(String.class)
        .block();
  }

}
