package com.worknector.offizz.domain.work.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class OperatingTime {
    private String operatingHoursMonday;

    private String operatingHoursTuesday;

    private String operatingHoursWednesday;

    private String operatingHoursThursday;

    private String operatingHoursFriday;

    private String operatingHoursSaturday;

    private String operatingHoursSunday;
}
