package com.worknector.offizz.openapi.tour.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class AccommodationResponse {

    private Response response;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Header header;
        private Body body;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Items items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Items {
        private List<Item> item;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private String addr1;
        private String cpyrhtDivCd;
        private String mapy;
        private String mlevel;
        private String modifiedtime;
        private String sigungucode;
        private String tel;
        private String title;
        private String contentid;
        private String contenttypeid;
        private String createdtime;
        private String benikia;
        private String goodstay;
        private String hanok;
        private String firstimage;
        private String firstimage2;
        private String mapx;
        private String addr2;
        private String areacode;
        private String booktour;
        private String cat1;
        private String cat2;
        private String cat3;
    }
}
