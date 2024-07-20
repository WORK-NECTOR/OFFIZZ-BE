package com.worknector.offizz.openapi.course.application.usecase;

import com.worknector.offizz.openapi.course.application.dto.CourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseOpenApiUseCase {

  private final WebClient.Builder webClientBuilder;

  @Value("${open-api.course.base-url}")
  private String baseUrl;

  @Value("${open-api.course.url-path}")
  private String urlPath;

  @Value("${open-api.mobile-os}")
  private String mobileOS;

  @Value("${open-api.mobile-app}")
  private String mobileApp;

  @Value("${open-api.service-key}")
  private String serviceKey;

  private WebClient courseWebClient() {
    DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
    factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

    return webClientBuilder
        .uriBuilderFactory(factory)
        .baseUrl(baseUrl)
        .build();
  }

  public CourseResponse fetchCourseData(int pageNo, int numOfRows) {
    return courseWebClient().get()
        .uri(uriBuilder -> uriBuilder
            .path(baseUrl)
            .queryParam("pageNo", pageNo)
            .queryParam("numOfRows", numOfRows)
            .queryParam("MobileOS", mobileOS)
            .queryParam("MobileApp", mobileApp)
            .queryParam("_type", "json")
            .queryParam("serviceKey", serviceKey)
            .build()
        )
        .retrieve()
        .bodyToMono(CourseResponse.class)
        .block();
  }
}
