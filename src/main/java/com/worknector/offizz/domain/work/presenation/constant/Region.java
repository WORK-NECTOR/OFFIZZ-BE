package com.worknector.offizz.domain.work.presenation.constant;

import java.util.Collections;
import java.util.List;

public enum Region {
    수도권, 경상권, 전라권, 충청권, 제주권, 강원권;

    public static List<String> findRegionList(Region region) {
        if (region.equals(수도권))
            return getSeoul();
        if (region.equals(경상권))
            return getGyeongsang();
        if (region.equals(전라권))
            return getJeolla();
        if (region.equals(충청권))
            return getChungcheong();
        if (region.equals(제주권))
            return getJeju();
        if (region.equals(강원권))
            return getGangwon();
        return Collections.emptyList();
    }

    private static List<String> getSeoul() {
        return List.of("서울", "경기", "인천");
    }

    private static List<String> getGyeongsang() {
        return List.of("부산", "대구", "울산", "경상남도", "경상북도", "경남", "경북");
    }

    private static List<String> getJeolla() {
        return List.of("광주", "전라남도", "전라북도", "전남", "전북");
    }

    private static List<String> getChungcheong() {
        return List.of("대전", "세종특별자치시", "충청남도", "충청북도", "충남", "충북");
    }

    private static List<String> getJeju() {
        return List.of("제주");
    }

    private static List<String> getGangwon() {
        return List.of("강원");
    }
}
