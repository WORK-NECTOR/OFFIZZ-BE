package com.worknector.offizz.database.office.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Office {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String officeName;

  private String cityName;

  private String districtName;

  private String dongName;

  private String liName;
  
  private String address;

  private String streetName;

  private String buildingNumber;

  private String latitude;

  private String longitude;

  private Integer zipCode;

  private String streetAddress;

  private String landAddress;

  private Integer price;

  private String priceType;

  private String officeType;

  private String capacity;

  private String operatingHoursMonday;

  private String operatingHoursTuesday;

  private String operatingHoursWednesday;

  private String operatingHoursThursday;

  private String operatingHoursFriday;

  private String operatingHoursSaturday;

  private String operatingHoursSunday;

  private String airConditioning;

  private String cafeRestaurant;

  private String copierPrinter;

  private String deliveryService;

  private String doorLock;

  private String powerOutlet;

  private String fax;

  private String twentyFourHoursOperation;

  private String openAllYear;

  private String heating;

  private String parking;

  private String publicLounge;

  private String sharedKitchen;

  private String waterPurifier;

  private String terraceRooftop;

  private String snacksDrinks;

  private String personalLocker;

  private String tvProjector;

  private String whiteboard;

  private String internetWifi;

  private String showerFacility;

  private String storage;

  private String lastUpdatedAt;
}
