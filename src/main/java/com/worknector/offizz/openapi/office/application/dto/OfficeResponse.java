package com.worknector.offizz.openapi.office.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;

@Getter
public class OfficeResponse {

  private int page;
  private int perPage;
  private int totalCount;
  private int currentCount;
  private int matchCount;
  private List<OfficeData> data;

  @Getter
  public static class OfficeData {
    @JsonProperty("시설명")
    private String officeName;
    @JsonProperty("시도 명칭")
    private String cityName;
    @JsonProperty("시군구 명칭")
    private String districtName;
    @JsonProperty("법정읍면동명칭")
    private String dongName;
    @JsonProperty("리 명칭")
    private String liName;
    @JsonProperty("번지")
    private String address;
    @JsonProperty("도로명 이름")
    private String streetName;
    @JsonProperty("건물 번호")
    private String buildingNumber;
    @JsonProperty("위도")
    private String latitude;
    @JsonProperty("경도")
    private String longitude;
    @JsonProperty("우편번호")
    private Integer zipCode;
    @JsonProperty("도로명주소")
    private String streetAddress;
    @JsonProperty("지번주소")
    private String landAddress;
    @JsonProperty("요금")
    private Integer price;
    @JsonProperty("이용요금단위")
    private String priceType;
    @JsonProperty("오피스 타입")
    private String officeType;
    @JsonProperty("수용인원")
    private String capacity;
    @JsonProperty("운영시간(월)")
    private String operatingHoursMonday;
    @JsonProperty("운영시간(화)")
    private String operatingHoursTuesday;
    @JsonProperty("운영시간(수)")
    private String operatingHoursWednesday;
    @JsonProperty("운영시간(목)")
    private String operatingHoursThursday;
    @JsonProperty("운영시간(금)")
    private String operatingHoursFriday;
    @JsonProperty("운영시간(토)")
    private String operatingHoursSaturday;
    @JsonProperty("운영시간(일)")
    private String operatingHoursSunday;
    @JsonProperty("에어컨")
    private String airConditioning;
    @JsonProperty("카페_레스토랑")
    private String cafeRestaurant;
    @JsonProperty("복사_인쇄기")
    private String copierPrinter;
    @JsonProperty("택배발송서비스")
    private String deliveryService;
    @JsonProperty("도어락")
    private String doorLock;
    @JsonProperty("콘센트")
    private String powerOutlet;
    @JsonProperty("팩스")
    private String fax;
    @JsonProperty("24시 운영")
    private String twentyFourHoursOperation;
    @JsonProperty("연중무휴")
    private String openAllYear;
    @JsonProperty("난방기")
    private String heating;
    @JsonProperty("주차")
    private String parking;
    @JsonProperty("공용라운지")
    private String publicLounge;
    @JsonProperty("공용주방")
    private String sharedKitchen;
    @JsonProperty("정수기")
    private String waterPurifier;
    @JsonProperty("테라스_루프탑")
    private String terraceRooftop;
    @JsonProperty("간단한 다과_음료")
    private String snacksDrinks;
    @JsonProperty("개인락커")
    private String personalLocker;
    @JsonProperty("티비_프로젝터")
    private String tvProjector;
    @JsonProperty("화이트보드")
    private String whiteboard;
    @JsonProperty("인터넷_와이파이")
    private String internetWifi;
    @JsonProperty("샤워시설")
    private String showerFacility;
    @JsonProperty("창고")
    private String storage;
    @JsonProperty("최종작성일")
    private String lastUpdatedAt;
  }
}
