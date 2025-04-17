package com.co.booking_ai.service.utils;

import com.co.booking_ai.service.constants.Constants;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

@Slf4j
public class Dates {

    public static final String UTC = "UTC";

    /**
     * Get ZonedDateTime in UTC-5
     *
     * @return ZonedDateTime
     */
    public static ZonedDateTime getZonedDateTime() {
        return ZonedDateTime.now(ZoneId.ofOffset(UTC, ZoneOffset.ofHours(Constants.UCT)));
    }

    /**
     * Get the current date in UTC-5
     *
     * @return Date
     */
    public static Date getCurrentDate() {
        return Date.from(Dates.getZonedDateTime().toInstant());
    }

    /**
     * Convert a Unix date (epoch time) to a Timestamp
     *
     * @param unixDate Unix date in seconds or milliseconds
     * @return Timestamp
     */
    public static Timestamp convertUnixToTimestamp(long unixDate) {
        // If the input is in seconds, convert it to milliseconds
        if (String.valueOf(unixDate).length() == 10) {
            unixDate *= 1000;
        }
        return new Timestamp(unixDate);
    }

    /**
     * Convert a Timestamp to a Unix date (epoch time in seconds)
     *
     * @param timestamp Timestamp to convert
     * @return Unix date in seconds
     */
    public static long convertTimestampToUnix(Timestamp timestamp) {
        return timestamp.getTime() / 1000;
    }

    /**
     * Get the current date in UTC
     *
     * @return Date
     */
    public static Date getCurrentDateWithUTC() {
        return Date.from(Dates.getZonedDateTime().toInstant());
    }

    /**
     * Convert a Date to a Unix date (epoch time in seconds)
     *
     * @param date Date to convert
     * @return Unix date in seconds
     */
    public static long convertDateToUnix(Date date) {
        return date.getTime() / 1000;
    }

    /**
     * Convert a Unix timestamp (epoch time in seconds) to a Date object.
     *
     * @param unixTimestamp Unix timestamp in seconds
     * @return Date object
     */
    public static Date convertUnixToDate(long unixTimestamp) {
        // Convert seconds to milliseconds
        return new Date(unixTimestamp * 1000);
    }

    public static int getDayIdOfWeek(long unixDate) {
        ZonedDateTime zonedDateTime = convertUnixToDate(unixDate).toInstant().atZone(ZoneId.ofOffset(UTC, ZoneOffset.ofHours(Constants.UCT)));
        return zonedDateTime.getDayOfWeek().getValue();
    }

    public static String getDayNameOfWeek(long unixDate) {
        ZonedDateTime zonedDateTime = convertUnixToDate(unixDate).toInstant().atZone(ZoneId.ofOffset(UTC, ZoneOffset.ofHours(Constants.UCT)));
        return zonedDateTime.getDayOfWeek().name();
    }

    /**
     * Convert a military hour (24-hour format) to AM/PM hour (12-hour format)
     *
     * @param militaryHour Military hour in "HH:mm" format
     * @return AM/PM hour in "hh:mm a" format
     */
    public static String convertMilitaryToAmPm(String militaryHour) {
        DateTimeFormatter militaryFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter amPmFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime time = LocalTime.parse(militaryHour, militaryFormatter);
        return time.format(amPmFormatter);
    }
    /**
     * Convert a AM/PM hour (12-hour format) to military hour (24-hour format)
     *
     * @param amPmHour AM/PM hour in "hh:mm a" format
     * @return Military hour in "HH:mm" format
     */
    public static String convertAmPmToMilitary(String amPmHour) {
        DateTimeFormatter amPmFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        DateTimeFormatter militaryFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.parse(amPmHour, amPmFormatter);
        return time.format(militaryFormatter);
    }

    /**
     * Validates if the given time string is in the correct AM/PM format (hh:mm a).
     *
     * @param time The time string to validate (e.g., "06:00 pm").
     * @return true if the time is valid, false otherwise.
     */
    public static boolean isValidAmPmTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        try {
            LocalTime.parse(time, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
