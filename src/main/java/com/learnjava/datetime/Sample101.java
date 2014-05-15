/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.learnjava.datetime;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;

/**
 *
 * @author vikmalik
 */
public class Sample101 {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.MAX;
        System.out.println("maximum Date Supported = "+ localDate);
        
        localDate = LocalDate.of(2014,12,21);
        System.out.println("Date is " + localDate);
        
        try{
            localDate = LocalDate.of(2014,12,41);
            System.out.println("Date is " + localDate);
        }catch(DateTimeException e){
            e.printStackTrace();
        }
        
        LocalTime localTime = LocalTime.MAX;
        System.out.println("Max Time is " + localTime);
        System.out.println("Min Time is " + localTime.MIN);
        
        localTime = LocalTime.of(11, 30);
        System.out.println("Time is " + localTime);

        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        System.out.println("Date time is " + localDateTime);
        
        
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        System.out.println("Zoned Date Time is = " + zonedDateTime);
        
        Instant now = Instant.now();
        System.out.println("Current TimeStamp = " + now);
        
        System.out.println("Zoned Datetime is after current time : " + zonedDateTime.isAfter(ZonedDateTime.ofInstant(now, ZoneId.systemDefault())));
        
        Period period = Period.between(localDate, LocalDate.now());
        System.out.println("Period between Local Date and now is " + period);
        
        Duration duration = Duration.between(localTime, ZonedDateTime.now());
        System.out.println("Duration between Local Datetime  and now is " + duration);

        System.out.println("Last Date of month of local Date is : " + localDate.with(java.time.temporal.TemporalAdjusters.lastDayOfMonth()));
    }
}
