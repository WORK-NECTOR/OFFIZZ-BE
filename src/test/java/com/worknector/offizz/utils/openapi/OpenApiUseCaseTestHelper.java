package com.worknector.offizz.utils.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

public class OpenApiUseCaseTestHelper {

    public static void setUpMockWebServer(MockWebServer mockWebServer, Object target, String baseUrlField) throws IOException {
        mockWebServer.start();
        String mockBaseUrl = mockWebServer.url("/").toString();
        ReflectionTestUtils.setField(target, baseUrlField, mockBaseUrl);
    }

    public static void setUpCommonFields(Object target, String[] fields, String[] values) {
        for (int i = 0; i < fields.length; i++) {
            ReflectionTestUtils.setField(target, fields[i], values[i]);
        }
    }

    public static void enqueueMockResponse(MockWebServer mockWebServer, Object response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String mockResponseBody = objectMapper.writeValueAsString(response);
        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponseBody)
                .addHeader("Content-Type", "application/json"));
    }
}
