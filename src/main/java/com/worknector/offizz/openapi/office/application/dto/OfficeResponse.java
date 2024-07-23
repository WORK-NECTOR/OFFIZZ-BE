package com.worknector.offizz.openapi.office.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OfficeResponse(

        int page,
        int perPage,
        int totalCount,
        int currentCount,
        int matchCount,
        List<OfficeData> data
) {

  public record OfficeData(

          @JsonProperty("시설명") String officeName,
          @JsonProperty("시도 명칭") String cityName,
          @JsonProperty("시군구 명칭") String districtName,
          @JsonProperty("법정읍면동명칭") String dongName,
          @JsonProperty("리 명칭") String liName,
          @JsonProperty("번지") String address,
          @JsonProperty("도로명 이름") String streetName,
          @JsonProperty("건물 번호") String buildingNumber,
          @JsonProperty("위도") String latitude,
          @JsonProperty("경도") String longitude,
          @JsonProperty("우편번호") Integer zipCode,
          @JsonProperty("도로명주소") String streetAddress,
          @JsonProperty("지번주소") String landAddress,
          @JsonProperty("요금") Integer price,
          @JsonProperty("이용요금단위") String priceType,
          @JsonProperty("오피스 타입") String officeType,
          @JsonProperty("수용인원") String capacity,
          @JsonProperty("운영시간(월)") String operatingHoursMonday,
          @JsonProperty("운영시간(화)") String operatingHoursTuesday,
          @JsonProperty("운영시간(수)") String operatingHoursWednesday,
          @JsonProperty("운영시간(목)") String operatingHoursThursday,
          @JsonProperty("운영시간(금)") String operatingHoursFriday,
          @JsonProperty("운영시간(토)") String operatingHoursSaturday,
          @JsonProperty("운영시간(일)") String operatingHoursSunday,
          @JsonProperty("에어컨") String airConditioning,
          @JsonProperty("카페_레스토랑") String cafeRestaurant,
          @JsonProperty("복사_인쇄기") String copierPrinter,
          @JsonProperty("택배발송서비스") String deliveryService,
          @JsonProperty("도어락") String doorLock,
          @JsonProperty("콘센트") String powerOutlet,
          @JsonProperty("팩스") String fax,
          @JsonProperty("24시 운영") String twentyFourHoursOperation,
          @JsonProperty("연중무휴") String openAllYear,
          @JsonProperty("난방기") String heating,
          @JsonProperty("주차") String parking,
          @JsonProperty("공용라운지") String publicLounge,
          @JsonProperty("공용주방") String sharedKitchen,
          @JsonProperty("정수기") String waterPurifier,
          @JsonProperty("테라스_루프탑") String terraceRooftop,
          @JsonProperty("간단한 다과_음료") String snacksDrinks,
          @JsonProperty("개인락커") String personalLocker,
          @JsonProperty("티비_프로젝터") String tvProjector,
          @JsonProperty("화이트보드") String whiteboard,
          @JsonProperty("인터넷_와이파이") String internetWifi,
          @JsonProperty("샤워시설") String showerFacility,
          @JsonProperty("창고") String storage,
          @JsonProperty("최종작성일") String lastUpdatedAt
  ) {}
}
