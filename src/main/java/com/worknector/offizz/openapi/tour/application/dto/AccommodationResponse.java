package com.worknector.offizz.openapi.tour.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public record AccommodationResponse(Response response) {

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
            String addr1,
            String cpyrhtDivCd,
            String mapy,
            String mlevel,
            String modifiedtime,
            String sigungucode,
            String tel,
            String title,
            String contentid,
            String contenttypeid,
            String createdtime,
            String benikia,
            String goodstay,
            String hanok,
            String firstimage,
            String firstimage2,
            String mapx,
            String addr2,
            String areacode,
            String booktour,
            String cat1,
            String cat2,
            String cat3) {}
}
