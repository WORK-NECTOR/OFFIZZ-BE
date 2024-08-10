package com.worknector.offizz.domain.work.office.application.dto.res;

import com.worknector.offizz.domain.work.office.domain.entity.PriceType;

public record RecOffice (
        long officeId,
        String name,
        Facilities facilities,
        String address,
        int price,
        PriceType priceType
){
    public record Facilities(
            boolean twentyFourHoursOperation,
            boolean openAllYear
    ){}
}
