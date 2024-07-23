package com.worknector.offizz.openapi.office.application.usecase;

import com.worknector.offizz.openapi.office.application.dto.OfficeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
@Transactional
@RequiredArgsConstructor
public class OfficeOpenApiUseCase {

  private final WebClient webClient;

  @Value("${open-api.office.base-url}")
  private String baseUrl;

  @Value("${open-api.office.url-path}")
  private String urlPath;

  @Value("${open-api.service-key}")
  private String serviceKey;

  public OfficeResponse fetchOfficeData(int page, int perPage) {
    DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
    factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

    return webClient.mutate()
            .uriBuilderFactory(factory)
            .baseUrl(baseUrl)
            .build()
            .get()
            .uri(uriBuilder -> uriBuilder
                    .path(urlPath)
                    .queryParam("page", page)
                    .queryParam("perPage", perPage)
                    .queryParam("returnType", "JSON")
                    .queryParam("serviceKey", serviceKey)
                    .build()
            )
            .retrieve()
            .bodyToMono(OfficeResponse.class)
            .block();
  }
}
