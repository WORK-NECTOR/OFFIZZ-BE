package com.worknector.offizz.openapi.tour.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record AreaCodeResponse(Response response) {

    public record Response(Header header, Body body) {}

    public record Header(String resultCode, String resultMsg) {}

    public record Body(
            @JsonInclude(JsonInclude.Include.NON_NULL)
            Items items,
            int numOfRows,
            int pageNo,
            int totalCount) {}

    public record Items(List<Item> item) {}

    public record Item(
            String code,
            String name,
            String rnum) {}
}