package com.worknector.offizz.openapi.tour.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record CafeResponse (Response response)
{
    public record Response(Header header, Body body) {}

    public record Body(
            @JsonInclude(JsonInclude.Include.NON_NULL)
            CafeResponse.Items items,
            int numOfRows,
            int pageNo,
            int totalCount) {}

    public record Header(String resultCode, String resultMsg) {}

    public record Items(List<Item> item) {}

    public record Item(
            String addr1,
            String add2,
            String areacode,
            String booktour,
            String cat1,
            String cat2,
            String cat3,
            String contentid,
            String contenttypeid,
            String createdtime,
            String firstimage,
            String firstimage2,
            String cpyrhtDivCd,
            String mapx,
            String mapy,
            String mlevel,
            String modifiedtime,
            String sigungucode,
            String tel,
            String title
    ) {}
}
