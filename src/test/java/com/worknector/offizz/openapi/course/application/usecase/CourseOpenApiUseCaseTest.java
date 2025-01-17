package com.worknector.offizz.openapi.course.application.usecase;

import com.worknector.offizz.openapi.course.application.dto.CourseResponse;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@CustomIntegrationTest
@DisplayName("CourseOpenApiUseCaseTest - 한국관광공사 두루누비 정보 API 테스트")
public class CourseOpenApiUseCaseTest {

    private MockWebServer mockWebServer;

    @InjectMocks
    private CourseOpenApiUseCase courseOpenApiUseCase;

    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        OpenApiUseCaseTestHelper.setUpMockWebServer(mockWebServer, courseOpenApiUseCase, "baseUrl");

        String[] fields = {"urlPath", "mobileOS", "mobileApp", "serviceKey"};
        String[] values = {"/B551011/Durunubi/courseList", "WIN", "OFFIZZ", "encodedKey"};
        OpenApiUseCaseTestHelper.setUpCommonFields(courseOpenApiUseCase, fields, values);

        WebClient webClient = WebClient.builder().build();
        ReflectionTestUtils.setField(courseOpenApiUseCase, "webClient", webClient);
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    @DisplayName("한국관광공사 두루누비 정보 API 호출에 성공한다.")
    public void testFetchCourseData_success() throws Exception {
        // given
        CourseResponse mockResponse = mockCourseResponse();
        OpenApiUseCaseTestHelper.enqueueMockResponse(mockWebServer, mockResponse);

        // when
        CourseResponse response = courseOpenApiUseCase.fetchCourseData(1, 10);

        // then
        RecordedRequest request = mockWebServer.takeRequest();
        assertThat(request.getPath()).isNotNull();
        assertThat(request.getPath().contains("/B551011/Durunubi/courseList")).isTrue();

        assertNotNull(response);
        assertEquals("0000", response.response().header().resultCode());
        assertEquals("OK", response.response().header().resultMsg());
        assertEquals(1, response.response().body().items().item().size());
    }

    @Test
    @DisplayName("한국관광공사 두루누비 정보 API 호출에 실패한다.")
    public void testFetchCourseData_failure() {
        // given
        mockWebServer.enqueue(new MockResponse().setResponseCode(500));

        // when & then
        WebClientResponseException exception = assertThrows(WebClientResponseException.class,
                () -> courseOpenApiUseCase.fetchCourseData(1, 10));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
    }

    private CourseResponse mockCourseResponse() {
        CourseResponse.Header header = new CourseResponse.Header("0000", "OK");
        CourseResponse.Item item = new CourseResponse.Item(
                "1",
                "비순환형",
                "산책로 구간에 대한 정보",
                "YYYYmmDDhhMMss",
                "사용자에게 제공하는 정보",
                "근처 관광지 정보",
                "산책로에 대한 정보",
                "T_ROUTE_index",
                "T_CRS_index",
                "코스 이름",
                "10",
                "10",
                "YYYYmmDDhhMMss",
                "시군",
                "DNWW",
                "https://www.durunubi.kr/editImgUp.do?filePath=/data/koreamobility/course/summap/crsIdx.gpx"
        );
        CourseResponse.Items items = new CourseResponse.Items(List.of(item));
        CourseResponse.Body body = new CourseResponse.Body(1, items, 10, 1);
        CourseResponse.Response response = new CourseResponse.Response(header, body);
        return new CourseResponse(response);
    }
}
