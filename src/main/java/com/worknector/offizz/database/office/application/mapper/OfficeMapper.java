package com.worknector.offizz.database.office.application.mapper;

import com.worknector.offizz.database.office.application.dto.OfficeResponse.OfficeData;
import com.worknector.offizz.database.office.domain.entity.Office;

public class OfficeMapper {

  private OfficeMapper() {
    throw new IllegalArgumentException();
  }

  public static Office toEntity(OfficeData dto) {
    return Office.builder()
        .officeName(dto.getOfficeName())
        .cityName(dto.getCityName())
        .districtName(dto.getDistrictName())
        .dongName(dto.getDongName())
        .liName(dto.getLiName())
        .address(dto.getAddress())
        .streetName(dto.getStreetName())
        .buildingNumber(dto.getBuildingNumber())
        .latitude(dto.getLatitude())
        .longitude(dto.getLongitude())
        .zipCode(dto.getZipCode())
        .streetAddress(dto.getStreetAddress())
        .landAddress(dto.getLandAddress())
        .price(dto.getPrice())
        .priceType(dto.getPriceType())
        .officeType(dto.getOfficeType())
        .capacity(dto.getCapacity())
        .operatingHoursMonday(dto.getOperatingHoursMonday())
        .operatingHoursTuesday(dto.getOperatingHoursTuesday())
        .operatingHoursWednesday(dto.getOperatingHoursWednesday())
        .operatingHoursThursday(dto.getOperatingHoursThursday())
        .operatingHoursFriday(dto.getOperatingHoursFriday())
        .operatingHoursSaturday(dto.getOperatingHoursSaturday())
        .operatingHoursSunday(dto.getOperatingHoursSunday())
        .airConditioning(dto.getAirConditioning())
        .cafeRestaurant(dto.getCafeRestaurant())
        .copierPrinter(dto.getCopierPrinter())
        .deliveryService(dto.getDeliveryService())
        .doorLock(dto.getDoorLock())
        .powerOutlet(dto.getPowerOutlet())
        .fax(dto.getFax())
        .twentyFourHoursOperation(dto.getTwentyFourHoursOperation())
        .openAllYear(dto.getOpenAllYear())
        .heating(dto.getHeating())
        .parking(dto.getParking())
        .publicLounge(dto.getPublicLounge())
        .sharedKitchen(dto.getSharedKitchen())
        .waterPurifier(dto.getWaterPurifier())
        .terraceRooftop(dto.getTerraceRooftop())
        .snacksDrinks(dto.getSnacksDrinks())
        .personalLocker(dto.getPersonalLocker())
        .tvProjector(dto.getTvProjector())
        .whiteboard(dto.getWhiteboard())
        .internetWifi(dto.getInternetWifi())
        .showerFacility(dto.getShowerFacility())
        .storage(dto.getStorage())
        .lastUpdatedAt(dto.getLastUpdatedAt())
        .build();
  }
}
