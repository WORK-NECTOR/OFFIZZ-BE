package com.worknector.offizz.domain.work.application.mapper;

import com.worknector.offizz.domain.work.application.dto.res.CafeAndOffice;
import com.worknector.offizz.domain.work.application.dto.res.OfficeDetailResponse;
import com.worknector.offizz.domain.work.application.dto.res.OfficeWithLatAndLon;
import com.worknector.offizz.domain.work.application.dto.res.RecOffice;
import com.worknector.offizz.domain.work.domain.entity.Cafe;
import com.worknector.offizz.domain.work.domain.entity.Office;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkMapper {
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

    public static CafeAndOffice mapToCafeAndOffice(Cafe cafe) {
        return new CafeAndOffice(
                "cafe",
                cafe.getCafeId(),
                cafe.getCafeName(),
                cafe.getStreetAddress(),
                cafe.getLat(),
                cafe.getLon(),
                cafe.getImage()
        );
    }

    public static CafeAndOffice mapToCafeAndOffice(Office office) {
        return new CafeAndOffice(
                "office",
                office.getOfficeId(),
                office.getOfficeName(),
                office.getStreetAddress(),
                office.getLat(),
                office.getLon(),
                null
        );
    }
}
