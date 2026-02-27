package com.ace.util;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class TimeUtils {

    public static ZoneOffset getSystemZoneOffset() {
        // Get the current date and time in the specified time zone
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());

        // Get the ZoneOffset from the ZoneId

        return zonedDateTime.getOffset();
    }

    public static LocalDate getNowDate() {
        return LocalDate.now();
    }
    public static LocalDate getYesterdayDate() {
        LocalDate now = LocalDate.now();
        LocalDate yesterday = now.minusDays(1);
        return yesterday;
    }
    public static LocalDate getTomorrowDate() {
        LocalDate now = LocalDate.now();
        LocalDate tomorrow = now.plusDays(1);
        return tomorrow;
    }
    public static long getTomorrowRemainSeconds() {
        //ZoneId z = ZoneId.of(ZoneId.systemDefault()) ;
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault()) ;
        LocalDate today = now.toLocalDate() ;
        ZonedDateTime startOfTomorrow = today.plusDays(1).atStartOfDay(ZoneId.systemDefault()) ;
        Duration duration = Duration.between(now , startOfTomorrow) ;
        return duration.getSeconds();
    }
    public static long getDayDiff(LocalDate date1, LocalDate date2) {
        return ChronoUnit.DAYS.between(date1, date2);
    }
    public static long getDayDiffFromNow(LocalDate date2) {
        return getDayDiff(getNowDate(), date2);
    }

    //instant
    public static Instant nowInstant() {
        return Instant.now();
    }
    public static int compareInstant(Instant instant1, Instant instant2) {
        return instant1.compareTo(instant2);
    }
    public static int compareNowWithInstant(Instant instant) {
        return compareInstant(nowInstant(), instant);
    }
    public static long durationInstant(Instant instant1, Instant instant2) {
        return Duration.between(instant1, instant2).getSeconds();
    }
    public static long durationInstantNow(Instant instant2) {
        return durationInstant(nowInstant(), instant2);
    }
    public static Instant startOfyesterdayInstant() {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDate yesterday = today.minusDays(1);
        ZonedDateTime startOfYesterday = yesterday.atStartOfDay(ZoneId.systemDefault());
        return startOfYesterday.toInstant();
    }
    public static Instant startOfTomorrowInstant() {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDate tomorrow = today.plusDays(1);
        ZonedDateTime startOfTomorrow = tomorrow.atStartOfDay(ZoneId.systemDefault());
        return startOfTomorrow.toInstant();
    }
    public static long startOfTomorrowRemain() {
        Instant now = Instant.now();
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDate tomorrow = today.plusDays(1);
        ZonedDateTime startOfTomorrow = tomorrow.atStartOfDay(ZoneId.systemDefault());
        Instant startOfTomorrowInstant = startOfTomorrow.toInstant();
        Duration duration = Duration.between(now, startOfTomorrowInstant);
        return duration.getSeconds();
    }
}
