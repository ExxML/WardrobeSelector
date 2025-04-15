package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClothingTest {
    
    private Clothing testClothing1;
    private Clothing testClothing2;

    @BeforeEach
    void runBefore() {
        testClothing1 = new Clothing("testClothing1");
        testClothing2 = new Clothing("testClothing2");
        testClothing2.setAvailability(false);
    }

    @Test
    void testConstructor1() {
        assertEquals("testClothing1", testClothing1.getClothingName());
        assertTrue(testClothing1.getAvailability());
    }

    @Test
    void testConstructor2() {
        assertEquals("testClothing2", testClothing2.getClothingName());
        assertFalse(testClothing2.getAvailability());
    }

    @Test
    void testToggleAvailabilitySingleFromTrue() {
        testClothing1.toggleAvailability();
        assertFalse(testClothing1.getAvailability());
    }

    @Test
    void testToggleAvailabilitySingleFromFalse() {
        testClothing2.toggleAvailability();
        assertTrue(testClothing2.getAvailability());
    }

    @Test
    void testToggleAvailabilityMultipleFromTrue() {
        testClothing1.toggleAvailability();
        assertFalse(testClothing1.getAvailability());
        testClothing1.toggleAvailability();
        assertTrue(testClothing1.getAvailability());
        testClothing1.toggleAvailability();
        assertFalse(testClothing1.getAvailability());
    }

    @Test
    void testToggleAvailabilityMultipleFromFalse() {
        testClothing2.toggleAvailability();
        assertTrue(testClothing2.getAvailability());
        testClothing2.toggleAvailability();
        assertFalse(testClothing2.getAvailability());
        testClothing2.toggleAvailability();
        assertTrue(testClothing2.getAvailability());
    }
    
    @Test
    void testSetClothingName() {
        testClothing1.setClothingName("changedTestClothing1");
        assertEquals("changedTestClothing1", testClothing1.getClothingName());
    }

}