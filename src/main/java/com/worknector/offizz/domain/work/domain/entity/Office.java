package com.worknector.offizz.domain.work.domain.entity;

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
  private Long officeId;

  private int hit;

  private String officeName;

  private String streetAddress;

  private Integer price;

  private PriceType priceType;

  private String officeType;

  private String capacity;

  private String lastUpdatedAt;

  private Facilities facilities;

  private OperatingTime operatingTime;

  private double lon;

  private double lat;
  public void updateHit() {
    this.hit++;
  }
}
