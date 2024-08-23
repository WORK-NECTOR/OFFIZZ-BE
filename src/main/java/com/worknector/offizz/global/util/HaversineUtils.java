package com.worknector.offizz.global.util;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.NumberTemplate;

public class HaversineUtils {

    private static final double EARTH_RADIUS = 6371.0;

    /**
     * 주어진 위도와 경도를 기준으로 Haversine 공식을 사용하여 거리를 계산
     *
     * @param userLat   사용자 위도
     * @param userLon   사용자 경도
     * @param lat       데이터의 위도
     * @param lon       데이터의 경도
     * @return          거리 계산에 대한 NumberTemplate
     */
    public static NumberTemplate<Double> distanceTemplate(double userLat, double userLon, NumberPath<Double> lat, NumberPath<Double> lon) {
        return Expressions.numberTemplate(
                Double.class,
                "{0} * acos(cos(radians({1})) * cos(radians({2})) * cos(radians({3}) - radians({4})) + sin(radians({1})) * sin(radians({2})))",
                EARTH_RADIUS,
                userLat,
                lat,
                lon,
                userLon
        );
    }
}
