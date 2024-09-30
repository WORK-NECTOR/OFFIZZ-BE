package com.worknector.offizz.domain.workation.application.dto.res;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record RecapResponse(
    FirstPage firstPage,
    SecondPage secondPage,
    ThirdPage thirdPage,
    FourthPage fourthPage,
    FifthPage fifthPage,
    SixthPage sixthPage,
    SeventhPage seventhPage,
    EighthPage eighthPage
) {
    public record FirstPage(String reason){}
    public record SecondPage(long totalTime, LocalTime startTime, LocalTime endTime, long averageTime){}
    public record ThirdPage(double rate){}
    public record FourthPage(LocalDate passionDate, long diff){}
    public record FifthPage(List<LocalDate> bestWorkDays){}
    public record SixthPage(int totalVacation, double averageRate){}
    public record SeventhPage(List<LocalDate> bestVacationDays){}
    public record EighthPage(List<BestVacation> bestVacations){}
    public record BestVacation(LocalDate date, String name){}
}
