package com.learnjava.datetime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author vikmalik
 */
public class DateTimeUtil {

    public static ZonedDateTime getZonedDateTime(String date, String time, String zoneName) {

        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
        ZoneId zoneId = ZoneId.of(zoneName);
        
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate, localTime, zoneId);
        return zonedDateTime;
    }

    /**
     * 
     * @param zonedDateTime
     * @return 
     */
    public static ZonedDateTime getZonedDateTime(String zonedDateTime) {
        ZonedDateTime date = ZonedDateTime.parse(zonedDateTime, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        return date;
    }
}
