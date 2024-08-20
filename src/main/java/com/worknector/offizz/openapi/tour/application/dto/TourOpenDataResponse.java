package com.worknector.offizz.openapi.tour.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record TourOpenDataResponse(Response response) {

    public record Response(Header header, Body body) {
    }

    public record Header(String resultCode, String resultMsg) {
    }

    public record Body(
            @JsonInclude(JsonInclude.Include.NON_NULL)
            Items items,
            int numOfRows,
            int pageNo,
            int totalCount) {
    }

    public record Items(List<Item> item) {
    }

    public record Item(
            String firstimage,
            String firstimage2,
            String mapx,
            String mapy,
            String mlevel,
            String addr2,
            String areacode,
            String modifiedtime,
            String cpyrhtDivCd,
            String booktour,
            String cat1,
            String sigungucode,
            String tel,
            String title,
            String addr1,
            String cat2,
            String cat3,
            String contentid,
            String contenttypeid,
            String createdtime,
            String zipcode) {
    }
}
