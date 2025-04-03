package com.co.booking_ai.service.utils;

import com.co.booking_ai.service.constants.Constants;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class Dates {
    /**
     * Get the current date in UTC-5
     *
     * @return Date
     */

    public static Date getCurrentDate() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.ofOffset("UTC", ZoneOffset.ofHours(Constants.UCT)));
        return Date.from(zonedDateTime.toInstant());
    }
}
