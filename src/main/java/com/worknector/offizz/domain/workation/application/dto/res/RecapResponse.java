package com.worknector.offizz.domain.workation.application.dto.res;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record RecapResponse(
    ZeroPage zeroPage,
    FirstPage firstPage,
    SecondPage secondPage,
    ThirdPage thirdPage,
    FourthPage fourthPage,
    FifthPage fifthPage,
    SixthPage sixthPage,
    SeventhPage seventhPage
) {
    public record ZeroPage(
            String name,
            String address,
            LocalDate startDate,
            LocalDate endDate
    ){}
    public record FirstPage(String reason){}
    public record SecondPage(long totalTime, LocalTime startTime, LocalTime endTime, long averageTime){}
    public record ThirdPage(double rate){}
    public record FourthPage(LocalDate passionDate, long diff){}
    public record FifthPage(int totalVacation, double averageRate){}
    public record SixthPage(double rate){}
    public record SeventhPage(List<BestVacation> bestVacations){}
    public record BestVacation(LocalDate date, String name){}
}
