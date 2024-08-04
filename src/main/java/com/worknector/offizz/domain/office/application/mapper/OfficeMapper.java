package com.worknector.offizz.domain.office.application.mapper;

import com.worknector.offizz.domain.office.application.dto.res.OfficeDetailResponse;
import com.worknector.offizz.domain.office.application.dto.res.RecOffice;
import com.worknector.offizz.domain.office.application.dto.res.RecOffice.Facilities;
import com.worknector.offizz.domain.office.domain.entity.Office;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OfficeMapper {
    public static RecOffice mapToRecOffice(Office office) {
        return new RecOffice(
                office.getId(),
                office.getOfficeName(),
                new Facilities(
                        office.getFacilities().isTwentyFourHoursOperation(),
                        office.getFacilities().isOpenAllYear()
                ),
                office.getStreetAddress(), office.getPrice(),
                office.getPriceType());
    }

    public static OfficeDetailResponse mapToOfficeDetail(Office office) {
        return new OfficeDetailResponse(
                office.getFacilities(),
                office.getCapacity(),
                office.getOperatingTime()
        );
    }
}
