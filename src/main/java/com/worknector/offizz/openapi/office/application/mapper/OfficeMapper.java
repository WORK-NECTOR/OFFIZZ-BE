package com.worknector.offizz.openapi.office.application.mapper;

import com.worknector.offizz.openapi.office.application.dto.OfficeResponse.OfficeData;
import com.worknector.offizz.openapi.office.domain.entity.Office;

public class OfficeMapper {

  private OfficeMapper() {
    throw new IllegalArgumentException();
  }

  public static Office toEntity(OfficeData dto) {
    return Office.builder()
            .officeName(dto.officeName())  // getter 메서드 대신 필드명 사용
            .cityName(dto.cityName())
            .districtName(dto.districtName())
            .dongName(dto.dongName())
            .liName(dto.liName())
            .address(dto.address())
            .streetName(dto.streetName())
            .buildingNumber(dto.buildingNumber())
            .latitude(dto.latitude())
            .longitude(dto.longitude())
            .zipCode(dto.zipCode())
            .streetAddress(dto.streetAddress())
            .landAddress(dto.landAddress())
            .price(dto.price())
            .priceType(dto.priceType())
            .officeType(dto.officeType())
            .capacity(dto.capacity())
            .operatingHoursMonday(dto.operatingHoursMonday())
            .operatingHoursTuesday(dto.operatingHoursTuesday())
            .operatingHoursWednesday(dto.operatingHoursWednesday())
            .operatingHoursThursday(dto.operatingHoursThursday())
            .operatingHoursFriday(dto.operatingHoursFriday())
            .operatingHoursSaturday(dto.operatingHoursSaturday())
            .operatingHoursSunday(dto.operatingHoursSunday())
            .airConditioning(dto.airConditioning())
            .cafeRestaurant(dto.cafeRestaurant())
            .copierPrinter(dto.copierPrinter())
            .deliveryService(dto.deliveryService())
            .doorLock(dto.doorLock())
            .powerOutlet(dto.powerOutlet())
            .fax(dto.fax())
            .twentyFourHoursOperation(dto.twentyFourHoursOperation())
            .openAllYear(dto.openAllYear())
            .heating(dto.heating())
            .parking(dto.parking())
            .publicLounge(dto.publicLounge())
            .sharedKitchen(dto.sharedKitchen())
            .waterPurifier(dto.waterPurifier())
            .terraceRooftop(dto.terraceRooftop())
            .snacksDrinks(dto.snacksDrinks())
            .personalLocker(dto.personalLocker())
            .tvProjector(dto.tvProjector())
            .whiteboard(dto.whiteboard())
            .internetWifi(dto.internetWifi())
            .showerFacility(dto.showerFacility())
            .storage(dto.storage())
            .lastUpdatedAt(dto.lastUpdatedAt())
            .build();
  }
}
