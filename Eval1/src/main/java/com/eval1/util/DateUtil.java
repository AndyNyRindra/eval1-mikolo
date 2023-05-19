package com.eval1.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    public static String formatSeconds (Double value) {
        int seconds = (int) Math.round(value);
        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - TimeUnit.DAYS.toHours(day);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.DAYS.toMinutes(day) - TimeUnit.HOURS.toMinutes(hours);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.DAYS.toSeconds(day) - TimeUnit.HOURS.toSeconds(hours) - TimeUnit.MINUTES.toSeconds(minute);
        return String.format("%d jours %d heures %d minutes %d secondes", day, hours, minute, second);
    }

    public static Double timeToHour (Time time) {
        return time.getHours() + time.getMinutes() / 60.0;
    }

    public static Time hourToTime (Double hour) {
        int hours = (int) Math.floor(hour);
        int minutes = (int) Math.round((hour - hours) * 60);
        return new Time(hours, minutes, 0);
    }


    public static String formatTime (Time time) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
        return sdf.format(time);
    }

    public static String formatDate (Timestamp timestamp) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy à HH:mm");
        return sdf.format(timestamp);
    }

    public static String formatFrenchTime (Time time) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH 'h' mm 'min'");
        return sdf.format(time);
    }

    public static LocalDateTime toLocalDateTime(String localDateTime){
        return LocalDateTime.parse(localDateTime, DateTimeFormatter.ISO_DATE_TIME);
    }

    public static LocalDate toLocalDate(String localDate){
        return LocalDate.parse(localDate, DateTimeFormatter.ISO_DATE);
    }

    public static Timestamp getTimestampFromDateAndTime(Date date, Time time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.YEAR, date.getYear() + 1900);
        calendar.set(Calendar.MONTH, date.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, date.getDate());

        return new Timestamp(calendar.getTimeInMillis());
    }

    public static String frenchDateInLetters (Timestamp date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEEE dd MMMM yyyy à HH:mm");
        return sdf.format(date);
    }

    public static String getMonth(Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM yyyy");
        return sdf.format(date);
    }
}
