package com.learnjava.datetime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vikmalik
 */
public class DateTimeUtilTest {
    
    public DateTimeUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetZonedDateTime_3args() {
        System.out.println("getZonedDateTime");
        
        ZonedDateTime result = DateTimeUtil.getZonedDateTime("2011-12-03", "10:15:30", "Europe/Paris");
        
        String expResult = "2011-12-03T10:15:30+01:00[Europe/Paris]";
        String actualResult = result.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        assertEquals(expResult, actualResult);
    }

    /**
     * Test of getZonedDateTime method, of class DateTimeUtil.
     */
    @Test
    public void testGetZonedDateTime_String() {
        System.out.println("getZonedDateTime");
        String zonedDateTime = "2011-12-03T10:15:30+01:00[Europe/Paris]";
        
        LocalDate localDate = LocalDate.parse("2011-12-03", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime localTime = LocalTime.parse("10:15:30", DateTimeFormatter.ISO_LOCAL_TIME);
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        
        ZonedDateTime expResult = ZonedDateTime.of(localDate, localTime,zoneId);
        ZonedDateTime result = DateTimeUtil.getZonedDateTime(zonedDateTime);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getZonedDateTime method, of class DateTimeUtil.
     */
    @Test
    public void testGetZonedDateTime_dst() {
        System.out.println("getZonedDateTime");
        String zonedDateTime = "2011-06-01T10:15:30+01:00[Europe/Paris]";
        
        LocalDate localDate = LocalDate.parse("2011-06-01", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime localTime = LocalTime.parse("10:15:30", DateTimeFormatter.ISO_LOCAL_TIME);
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        
        ZonedDateTime expResult = ZonedDateTime.of(localDate, localTime,zoneId);
        ZonedDateTime result = DateTimeUtil.getZonedDateTime(zonedDateTime);
        assertEquals(expResult, result);
    }
    
}
