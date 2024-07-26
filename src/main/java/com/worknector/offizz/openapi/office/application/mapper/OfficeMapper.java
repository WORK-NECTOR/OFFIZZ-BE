package com.worknector.offizz.openapi.office.application.mapper;

import com.worknector.offizz.openapi.office.application.dto.OfficeResponse.OfficeData;
import com.worknector.offizz.domain.office.domain.entity.Facilities;
import com.worknector.offizz.domain.office.domain.entity.Office;
import com.worknector.offizz.domain.office.domain.entity.OperatingTime;

public class OfficeMapper {

  private OfficeMapper() {
    throw new IllegalArgumentException();
  }

  public static Office toEntity(OfficeData dto) {
    return Office.builder()
            .officeName(dto.officeName())
            .streetAddress(dto.streetAddress())
            .price(dto.price())
            .priceType(dto.priceType())
            .officeType(dto.officeType())
            .capacity(dto.capacity())
            .lastUpdatedAt(dto.lastUpdatedAt())
            .operatingTime(mapToOperatingTime(dto))
            .facilities(mapToFacilities(dto))
            .build();
  }

  private static OperatingTime mapToOperatingTime(OfficeData dto) {
    return OperatingTime.builder()
            .operatingHoursMonday(dto.operatingHoursMonday())
            .operatingHoursTuesday(dto.operatingHoursTuesday())
            .operatingHoursWednesday(dto.operatingHoursWednesday())
            .operatingHoursThursday(dto.operatingHoursThursday())
            .operatingHoursFriday(dto.operatingHoursFriday())
            .operatingHoursSaturday(dto.operatingHoursSaturday())
            .operatingHoursSunday(dto.operatingHoursSunday())
            .build();
  }

  private static Facilities mapToFacilities(OfficeData dto) {
    return Facilities.builder()
            .airConditioning(isPresent(dto.airConditioning()))
            .cafeRestaurant(isPresent(dto.cafeRestaurant()))
            .copierPrinter(isPresent(dto.copierPrinter()))
            .deliveryService(isAvailable(dto.deliveryService()))
            .doorLock(isPresent(dto.doorLock()))
            .powerOutlet(isPresent(dto.doorLock()))
            .fax(isAvailable(dto.fax()))
            .twentyFourHoursOperation(contains24Hours(dto.twentyFourHoursOperation()))
            .openAllYear(isOpenAllYear(dto.openAllYear()))
            .heating(isPresent(dto.heating()))
            .parking(isAvailable(dto.parking()))
            .publicLounge(isPresent(dto.publicLounge()))
            .sharedKitchen(isPresent(dto.sharedKitchen()))
            .waterPurifier(isPresent(dto.waterPurifier()))
            .terraceRooftop(isPresent(dto.terraceRooftop()))
            .snacksDrinks(isPresent(dto.snacksDrinks()))
            .personalLocker(isPresent(dto.personalLocker()))
            .tvProjector(isPresent(dto.tvProjector()))
            .whiteboard(isPresent(dto.whiteboard()))
            .internetWifi(isPresent(dto.internetWifi()))
            .showerFacility(isPresent(dto.showerFacility()))
            .storage(isAvailable(dto.storage()))
            .build();
  }

  private static boolean isAvailable(String value) {
    return value.contains("가능");
  }

  private static boolean contains24Hours(String value) {
    return value.contains("24시");
  }

  private static boolean isOpenAllYear(String value) {
    return value.contains("없음");
  }

  private static boolean isPresent(String value) {
    return value.contains("있음") || !value.contains("미보유");
  }
}
