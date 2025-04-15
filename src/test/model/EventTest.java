package model;

import model.event.Event;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The following class was modeled on https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
 * Unit tests for the Event class
 */
public class EventTest {
    
    private Event event;
    private Date date;
    
    @BeforeEach
    public void runBefore() {
        event = new Event("A1");   // (1)
        date = Calendar.getInstance().getTime();   // (2)
    }
    
    @Test
    public void testEvent() {
        assertEquals("A1", event.getDescription());
        // assertEquals(d, e.getDate());
        // Lines (1) and (2) must run in same millisecond for this test to make sense and pass.
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "A1", event.toString());
    }

}