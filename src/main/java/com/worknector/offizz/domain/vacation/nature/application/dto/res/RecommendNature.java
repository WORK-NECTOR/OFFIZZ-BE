package com.worknector.offizz.domain.vacation.nature.application.dto.res;

public record RecommendNature(
        Natures natures,
        Courses courses
) {
    public record Natures(
            Long natureId,
            String addr1,
            double lon,
            double lat,
            String tel,
            String title,
            String firstimage,
            String firstimage2,
            long hit
    ){}

    public record Courses(
            Long courseId,
            String crsContents,
            String travelerinfo,
            String crsTourInfo,
            String crsSummary,
            String crsKorNm,
            String crsDstnc,
            String crsTotlRqrmHour,
            String gpxpath,
            long hit
    ){}
}
