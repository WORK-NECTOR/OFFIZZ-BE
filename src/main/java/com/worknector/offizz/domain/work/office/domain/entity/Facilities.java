package com.worknector.offizz.domain.work.office.domain.entity;

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
public class Facilities {
    private boolean heating;

    private boolean parking;

    private boolean publicLounge;

    private boolean sharedKitchen;

    private boolean waterPurifier;

    private boolean terraceRooftop;

    private boolean snacksDrinks;

    private boolean personalLocker;

    private boolean tvProjector;

    private boolean whiteboard;

    private boolean internetWifi;

    private boolean showerFacility;

    private boolean storage;

    private boolean doorLock;

    private boolean powerOutlet;

    private boolean fax;

    private boolean twentyFourHoursOperation;

    private boolean openAllYear;

    private boolean airConditioning;

    private boolean cafeRestaurant;

    private boolean copierPrinter;

    private boolean deliveryService;
}
