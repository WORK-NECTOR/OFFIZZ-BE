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
            .airConditioning(!dto.airConditioning().contains("미보유"))
            .cafeRestaurant(!dto.cafeRestaurant().contains("미보유"))
            .copierPrinter(!dto.copierPrinter().contains("미보유"))
            .deliveryService(dto.deliveryService().contains("가능"))
            .doorLock(!dto.doorLock().contains("미보유"))
            .powerOutlet(!dto.doorLock().contains("미보유"))
            .fax(dto.fax().contains("가능"))
            .twentyFourHoursOperation(dto.twentyFourHoursOperation().contains("24시"))
            .openAllYear(dto.openAllYear().contains("없음"))
            .heating(!dto.heating().contains("미보유"))
            .parking(dto.parking().contains("가능"))
            .publicLounge(dto.publicLounge().contains("있음"))
            .sharedKitchen(!dto.sharedKitchen().contains("미보유"))
            .waterPurifier(!dto.waterPurifier().contains("미보유"))
            .terraceRooftop(!dto.terraceRooftop().contains("미보유"))
            .snacksDrinks(!dto.snacksDrinks().contains("미보유"))
            .personalLocker(!dto.personalLocker().contains("미보유"))
            .tvProjector(!dto.tvProjector().contains("미보유"))
            .whiteboard(!dto.whiteboard().contains("미보유"))
            .internetWifi(!dto.internetWifi().contains("미보유"))
            .showerFacility(!dto.showerFacility().contains("미보유"))
            .storage(dto.storage().contains("사용가능"))
            .build();
  }
}
