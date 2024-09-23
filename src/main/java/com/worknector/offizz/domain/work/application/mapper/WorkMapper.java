package com.worknector.offizz.domain.work.application.mapper;

import com.worknector.offizz.domain.work.application.dto.res.*;
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

    public static CafeAndOffice mapToCafeAndOffice(SelectCafe selectCafe) {
        Cafe cafe = selectCafe.cafe();
        return new CafeAndOffice(
                "cafe",
                cafe.getCafeId(),
                cafe.getCafeName(),
                cafe.getStreetAddress(),
                cafe.getLat(),
                cafe.getLon(),
                cafe.getImage(),
                selectCafe.isLike()
        );
    }

    public static CafeAndOffice mapToCafeAndOffice(SelectOffice selectOffice) {
        Office office = selectOffice.office();
        return new CafeAndOffice(
                "office",
                office.getOfficeId(),
                office.getOfficeName(),
                office.getStreetAddress(),
                office.getLat(),
                office.getLon(),
                null,
                selectOffice.isLike()
        );
    }
}
