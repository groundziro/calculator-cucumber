package junit5tests;

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestTime {
    Time t;
    String hour0;
    String hour1;
    String hour2;
    String hour3;
    String hour4;
    String hour5;
    String hour6;
    String hour7;
    LocalDate date1;
    LocalDate date2;

    @BeforeEach
    public void setUp() {
        t = new Time();
        hour0 = "12:00";
        hour1 = "12:00:00";
        hour2 = "12 AM";
        hour3 = "12:10 PM";
        hour4 = "12:23:23 PM";
        hour5 = "24:00";
        hour6 = "23:64";
        hour7 = "1 PM GMT+4";
        date1 = LocalDate.of(2021,4,30);
        date2 = LocalDate.of(2021, 4, 29);
    }

    @Test
    public void hour_well_formatted0() {
        assertTrue(Time.hours_well_formatted(hour0));
        assertTrue(Time.hours_well_formatted(hour1));
        assertTrue(Time.hours_well_formatted(hour2));
        assertTrue(Time.hours_well_formatted(hour3));
        assertTrue(Time.hours_well_formatted(hour4));
        assertTrue(Time.hours_well_formatted(hour7));
        assertFalse(Time.hours_well_formatted(hour5));
        assertFalse(Time.hours_well_formatted(hour6));
    }

//    Since the value always change, I commented this test.
//    @Test
//    public void elasped_since() {
//        assertEquals("201 minutes", t.elapsed_since(date1, hour0, "Minutes"));
//    }

    @Test
    public void elapsed_between() {
        assertEquals("0 days, 18 hours, 0 minutes and 0 seconds.", t.elapsed_between(date2, hour7, "Mixe", date1, hour1));
    }

//    Commentend since the current date always change.
//    @Test
//    public void plus() {
//        assertEquals("2021-05-01 18:25:31", t.plus("1", "Days"));
//    }

//    Commentend since the current date always change.
//    @Test
//    public void minus() {
//        assertEquals("2021-04-30 18:25:31", t.minus("1", "Days"));
//    }
}
