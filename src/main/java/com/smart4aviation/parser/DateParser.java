package com.smart4aviation.parser;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateParser {
    private final static String patternDate = "yyyy-MM-dd HH:mm:ss";
    private final static String patternDateTime = "yyyy-MM-dd HH:mm:ss";

    public LocalDateTime parseDate(String date) {
        date += " 00:00:00";
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(patternDate));
    }

    public LocalDateTime parseDateTime(String dateTime) {
        int index = dateTime.indexOf("T");
        dateTime = dateTime.substring(0, index) + " " + dateTime.substring(index + 1, dateTime.length() - 6);
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(patternDateTime));
    }
}