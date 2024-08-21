package com.worknector.offizz.domain.work.office.application.mapper;

import com.worknector.offizz.domain.work.office.application.dto.res.OfficeDetailResponse;
import com.worknector.offizz.domain.work.office.application.dto.res.OfficeWithLatAndLon;
import com.worknector.offizz.domain.work.office.application.dto.res.RecOffice;
import com.worknector.offizz.domain.work.office.domain.entity.Office;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OfficeMapper {
    public static RecOffice mapToRecOffice(Office office) {
        return new RecOffice(
                office.getOfficeId(),
                office.getOfficeName(),
                new RecOffice.Facilities(
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

    public static OfficeWithLatAndLon mapToOfficeWithLatAndLon(Office office) {
        return new OfficeWithLatAndLon(
                office.getOfficeId(),
                office.getOfficeName(),
                office.getStreetAddress(),
                office.getLat(),
                office.getLon()
        );
    }
}
