package com.worknector.offizz.openapi.office.application.usecase;

import com.worknector.offizz.openapi.office.application.dto.OfficeResponse;
import com.worknector.offizz.utils.annotation.CustomIntegrationTest;
import com.worknector.offizz.utils.openapi.OpenApiUseCaseTestHelper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
import java.util.List;

import static com.worknector.offizz.domain.office.domain.entity.PriceType.월;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@CustomIntegrationTest
@DisplayName("OfficeOpenApiUseCaseTest - 공유오피스 API 테스트")
class OfficeOpenApiUseCaseTest {

    private MockWebServer mockWebServer;

    @InjectMocks
    private OfficeOpenApiUseCase officeOpenApiUseCase;

    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        OpenApiUseCaseTestHelper.setUpMockWebServer(mockWebServer, officeOpenApiUseCase, "baseUrl");

        String[] fields = {"urlPath", "serviceKey"};
        String[] values = {"/15111411/v1/uddi:d93c5174-47eb-4eb2-830a-b98e595fa163", "encodedKey"};
        OpenApiUseCaseTestHelper.setUpCommonFields(officeOpenApiUseCase, fields, values);

        WebClient webClient = WebClient.builder().build();
        ReflectionTestUtils.setField(officeOpenApiUseCase, "webClient", webClient);
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    @DisplayName("공유오피스 API 호출에 성공한다.")
    public void testFetchOfficeData_success() throws Exception {
        // given
        OfficeResponse mockResponse = mockOfficeResponse();
        OpenApiUseCaseTestHelper.enqueueMockResponse(mockWebServer, mockResponse);

        // when
        OfficeResponse response = officeOpenApiUseCase.fetchOfficeData(1, 10);

        // then
        RecordedRequest request = mockWebServer.takeRequest();
        assertThat(request.getPath()).isNotNull();
        assertThat(request.getPath().contains("/15111411/v1/uddi:d93c5174-47eb-4eb2-830a-b98e595fa163")).isTrue();

        assertNotNull(response);
        assertEquals(1, response.data().size());
    }

    @Test
    @DisplayName("공유오피스 API 호출에 실패한다.")
    public void testFetchOfficeData_failure() {
        // given
        mockWebServer.enqueue(new MockResponse().setResponseCode(500));

        // when & then
        WebClientResponseException exception = assertThrows(WebClientResponseException.class,
                () -> officeOpenApiUseCase.fetchOfficeData(1, 10));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
    }

    private OfficeResponse mockOfficeResponse() {
        OfficeResponse.OfficeData officeData = new OfficeResponse.OfficeData(
                "Office Name", "City Name", "District Name", "Dong Name", "Li Name",
                "Address", "Street Name", "Building Number", "Latitude", "Longitude",
                12345, "Street Address", "Land Address", 1000, 월,
                "Office Type", "Capacity", "9:00 AM - 6:00 PM", "9:00 AM - 6:00 PM",
                "9:00 AM - 6:00 PM", "9:00 AM - 6:00 PM", "9:00 AM - 6:00 PM",
                "Closed", "Closed", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
                "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
                "Yes", "Yes", "Yes", "Yes", "2023-01-01", ""
        );

        return new OfficeResponse(1, 10, 1, 1, 1, List.of(officeData));
    }
}
