package com.example.krkapartments.util;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

@Service
public class DateTimeProvider {

    public static LocalDate getLocalDateToday() {
        return LocalDate.now();
    }

    public static LocalDateTime getLocalDateTimeNowTruncatedToSeconds() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    public static LocalDate getFirstDayOfCurrentMonth() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
    }
}