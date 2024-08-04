package com.worknector.offizz.domain.office.application.dto.req;

import java.util.Arrays;
import java.util.List;

public enum Region {
    서울, 경기, 인천, 대구, 대전, 충청, 경상, 부산, 기타;

    public boolean isEtc() {
        return this == 기타;
    }

    public static List<Region> getNonEtcRegions() {
        return Arrays.stream(values())
                .filter(region -> region != 기타)
                .toList();
    }
}
