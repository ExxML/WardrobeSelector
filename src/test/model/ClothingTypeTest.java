package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClothingTypeTest {

    Clothing testClothing1;
    Clothing testClothing2;
    Clothing testClothing3;
    Clothing testClothing1Unavailable;
    Wardrobe testWardrobe;

    @BeforeEach
    void runBefore() {
        testClothing1 = new Clothing("testClothing1");
        testClothing2 = new Clothing("testClothing2");
        testClothing3 = new Clothing("testClothing3");
        testClothing1Unavailable = new Clothing("testClothing1");
        testClothing1Unavailable.setAvailability(false);
        testWardrobe = new Wardrobe();
    }

    @Test
    void testConstructor() {
        assertEquals(new ArrayList<Clothing>(), testWardrobe.getLongSleeveTops());
        assertEquals(new ArrayList<Clothing>(), testWardrobe.getShortSleeveTops());
        assertEquals(new ArrayList<Clothing>(), testWardrobe.getLongBottoms());
        assertEquals(new ArrayList<Clothing>(), testWardrobe.getShortBottoms());
    }

    // Add clothing

    @Test
    void testAddLongSleeveTopSingle() {
        testWardrobe.addLongSleeveTop(testClothing1);
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1)), testWardrobe.getLongSleeveTops());
    }

    @Test
    void testAddLongSleeveTopMultiple() {
        testWardrobe.addLongSleeveTop(testClothing1);
        testWardrobe.addLongSleeveTop(testClothing2);
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2)),
                testWardrobe.getLongSleeveTops());
    }

    @Test
    void testAddShortSleeveTopSingle() {
        testWardrobe.addShortSleeveTop(testClothing1);
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1)), testWardrobe.getShortSleeveTops());
    }

    @Test
    void testAddShortSleeveTopMultiple() {
        testWardrobe.addShortSleeveTop(testClothing1);
        testWardrobe.addShortSleeveTop(testClothing2);
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2)),
                testWardrobe.getShortSleeveTops());
    }

    @Test
    void testAddLongBottomSingle() {
        testWardrobe.addLongBottom(testClothing1);
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1)), testWardrobe.getLongBottoms());
    }

    @Test
    void testAddLongBottomMultiple() {
        testWardrobe.addLongBottom(testClothing1);
        testWardrobe.addLongBottom(testClothing2);
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2)),
                testWardrobe.getLongBottoms());
    }

    @Test
    void testAddShortBottomSingle() {
        testWardrobe.addShortBottom(testClothing1);
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1)), testWardrobe.getShortBottoms());
    }

    @Test
    void testAddShortBottomMultiple() {
        testWardrobe.addShortBottom(testClothing1);
        testWardrobe.addShortBottom(testClothing2);
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2)),
                testWardrobe.getShortBottoms());
    }

    // Remove clothing

    @Test
    void testRemoveLongSleeveTopFirst() {
        testWardrobe.addLongSleeveTop(testClothing1);
        testWardrobe.addLongSleeveTop(testClothing2);
        testWardrobe.addLongSleeveTop(testClothing3);
        assertEquals("Long Sleeve Tops", testWardrobe.removeClothing("testClothing1"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing2, testClothing3)), 
                testWardrobe.getLongSleeveTops());
    }

    @Test
    void testRemoveLongSleeveTopLast() {
        testWardrobe.addLongSleeveTop(testClothing1);
        testWardrobe.addLongSleeveTop(testClothing2);
        testWardrobe.addLongSleeveTop(testClothing3);
        assertEquals("Long Sleeve Tops", testWardrobe.removeClothing("testClothing3"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2)), 
                testWardrobe.getLongSleeveTops());
    }

    @Test
    void testRemoveLongSleeveTopDoesNotContain() {
        testWardrobe.addLongSleeveTop(testClothing1);
        testWardrobe.addLongSleeveTop(testClothing2);
        testWardrobe.addLongSleeveTop(testClothing3);
        assertEquals("", testWardrobe.removeClothing("testClothing4"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2, testClothing3)), 
                testWardrobe.getLongSleeveTops());
    }

    @Test
    void testRemoveShortSleeveTopFirst() {
        testWardrobe.addShortSleeveTop(testClothing1);
        testWardrobe.addShortSleeveTop(testClothing2);
        testWardrobe.addShortSleeveTop(testClothing3);
        assertEquals("Short Sleeve Tops", testWardrobe.removeClothing("testClothing1"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing2, testClothing3)), 
                testWardrobe.getShortSleeveTops());
    }

    @Test
    void testRemoveShortSleeveTopLast() {
        testWardrobe.addShortSleeveTop(testClothing1);
        testWardrobe.addShortSleeveTop(testClothing2);
        testWardrobe.addShortSleeveTop(testClothing3);
        assertEquals("Short Sleeve Tops", testWardrobe.removeClothing("testClothing3"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2)), 
                testWardrobe.getShortSleeveTops());
    }

    @Test
    void testRemoveShortSleeveTopDoesNotContain() {
        testWardrobe.addShortSleeveTop(testClothing1);
        testWardrobe.addShortSleeveTop(testClothing2);
        testWardrobe.addShortSleeveTop(testClothing3);
        assertEquals("", testWardrobe.removeClothing("testClothing4"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2, testClothing3)), 
                testWardrobe.getShortSleeveTops());
    }

    @Test
    void testRemoveLongBottomFirst() {
        testWardrobe.addLongBottom(testClothing1);
        testWardrobe.addLongBottom(testClothing2);
        testWardrobe.addLongBottom(testClothing3);
        assertEquals("Long Bottoms", testWardrobe.removeClothing("testClothing1"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing2, testClothing3)), 
                testWardrobe.getLongBottoms());
    }

    @Test
    void testRemoveLongBottomLast() {
        testWardrobe.addLongBottom(testClothing1);
        testWardrobe.addLongBottom(testClothing2);
        testWardrobe.addLongBottom(testClothing3);
        assertEquals("Long Bottoms", testWardrobe.removeClothing("testClothing3"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2)), 
                testWardrobe.getLongBottoms());
    }

    @Test
    void testRemoveLongBottomDoesNotContain() {
        testWardrobe.addLongBottom(testClothing1);
        testWardrobe.addLongBottom(testClothing2);
        testWardrobe.addLongBottom(testClothing3);
        assertEquals("", testWardrobe.removeClothing("testClothing4"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2, testClothing3)), 
                testWardrobe.getLongBottoms());
    }

    @Test
    void testRemoveShortBottomFirst() {
        testWardrobe.addShortBottom(testClothing1);
        testWardrobe.addShortBottom(testClothing2);
        testWardrobe.addShortBottom(testClothing3);
        assertEquals("Short Bottoms", testWardrobe.removeClothing("testClothing1"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing2, testClothing3)), 
                testWardrobe.getShortBottoms());
    }

    @Test
    void testRemoveShortBottomLast() {
        testWardrobe.addShortBottom(testClothing1);
        testWardrobe.addShortBottom(testClothing2);
        testWardrobe.addShortBottom(testClothing3);
        assertEquals("Short Bottoms", testWardrobe.removeClothing("testClothing3"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2)), 
                testWardrobe.getShortBottoms());
    }

    @Test
    void testRemoveShortBottomDoesNotContain() {
        testWardrobe.addShortBottom(testClothing1);
        testWardrobe.addShortBottom(testClothing2);
        testWardrobe.addShortBottom(testClothing3);
        assertEquals("", testWardrobe.removeClothing("testClothing4"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2, testClothing3)), 
                testWardrobe.getShortBottoms());
    }

    // Select random clothing

    // May fail due to randomness
    @Test
    void testSelectRandomLongSleeveTop() {
        testWardrobe.addLongSleeveTop(testClothing1);
        testWardrobe.addLongSleeveTop(testClothing1Unavailable);
        testWardrobe.addLongSleeveTop(testClothing3);
        int testClothing1Counter = 0;
        int testClothing1UnavailableCounter = 0;
        for (int i = 0; i < 1000; i++) {
            if (testWardrobe.selectRandomLongSleeveTop().equals(testClothing1)) {
                testClothing1Counter++;
            }
            if (testWardrobe.selectRandomLongSleeveTop().equals(testClothing1Unavailable)) {
                testClothing1UnavailableCounter++;
            }
        }
        assertFalse(testClothing1Counter == 0);
        assertFalse(testClothing1Counter == 1000);
        assertTrue(testClothing1UnavailableCounter == 0);
    }

    @Test
    void testSelectRandomLongSleeveTopNoneAvailable() {
        testClothing1.setAvailability(false);
        testClothing2.setAvailability(false);
        testClothing3.setAvailability(false);
        testWardrobe.addLongSleeveTop(testClothing1);
        testWardrobe.addLongSleeveTop(testClothing2);
        testWardrobe.addLongSleeveTop(testClothing3);
        int nullCounter = 0;
        for (int i = 0; i < 1000; i++) {
            if (testWardrobe.selectRandomLongSleeveTop() == null) {
                nullCounter++;
            }
        }
        assertTrue(nullCounter == 1000);
    }

    // May fail due to randomness
    @Test
    void testSelectRandomShortSleeveTop() {
        testWardrobe.addShortSleeveTop(testClothing1);
        testWardrobe.addShortSleeveTop(testClothing1Unavailable);
        testWardrobe.addShortSleeveTop(testClothing3);
        int testClothing1Counter = 0;
        int testClothing1UnavailableCounter = 0;
        for (int i = 0; i < 1000; i++) {
            if (testWardrobe.selectRandomShortSleeveTop().equals(testClothing1)) {
                testClothing1Counter++;
            }
            if (testWardrobe.selectRandomShortSleeveTop().equals(testClothing1Unavailable)) {
                testClothing1UnavailableCounter++;
            }
        }
        assertFalse(testClothing1Counter == 0);
        assertFalse(testClothing1Counter == 1000);
        assertTrue(testClothing1UnavailableCounter == 0);
    }

    @Test
    void testSelectRandomShortSleeveTopNoneAvailable() {
        testClothing1.setAvailability(false);
        testClothing2.setAvailability(false);
        testClothing3.setAvailability(false);
        testWardrobe.addShortSleeveTop(testClothing1);
        testWardrobe.addShortSleeveTop(testClothing2);
        testWardrobe.addShortSleeveTop(testClothing3);
        int nullCounter = 0;
        for (int i = 0; i < 1000; i++) {
            if (testWardrobe.selectRandomShortSleeveTop() == null) {
                nullCounter++;
            }
        }
        assertTrue(nullCounter == 1000);
    }

    // May fail due to randomness
    @Test
    void testSelectRandomLongBottom() {
        testWardrobe.addLongBottom(testClothing1);
        testWardrobe.addLongBottom(testClothing1Unavailable);
        testWardrobe.addLongBottom(testClothing3);
        int testClothing1Counter = 0;
        int testClothing1UnavailableCounter = 0;
        for (int i = 0; i < 1000; i++) {
            if (testWardrobe.selectRandomLongBottom().equals(testClothing1)) {
                testClothing1Counter++;
            }
            if (testWardrobe.selectRandomLongBottom().equals(testClothing1Unavailable)) {
                testClothing1UnavailableCounter++;
            }
        }
        assertFalse(testClothing1Counter == 0);
        assertFalse(testClothing1Counter == 1000);
        assertTrue(testClothing1UnavailableCounter == 0);
    }

    @Test
    void testSelectRandomLongBottomNoneAvailable() {
        testClothing1.setAvailability(false);
        testClothing2.setAvailability(false);
        testClothing3.setAvailability(false);
        testWardrobe.addLongBottom(testClothing1);
        testWardrobe.addLongBottom(testClothing2);
        testWardrobe.addLongBottom(testClothing3);
        int nullCounter = 0;
        for (int i = 0; i < 1000; i++) {
            if (testWardrobe.selectRandomLongBottom() == null) {
                nullCounter++;
            }
        }
        assertTrue(nullCounter == 1000);
    }

    // May fail due to randomness
    @Test
    void testSelectRandomShortBottom() {
        testWardrobe.addShortBottom(testClothing1);
        testWardrobe.addShortBottom(testClothing1Unavailable);
        testWardrobe.addShortBottom(testClothing3);
        int testClothing1Counter = 0;
        int testClothing1UnavailableCounter = 0;
        for (int i = 0; i < 1000; i++) {
            if (testWardrobe.selectRandomShortBottom().equals(testClothing1)) {
                testClothing1Counter++;
            }
            if (testWardrobe.selectRandomShortBottom().equals(testClothing1Unavailable)) {
                testClothing1UnavailableCounter++;
            }
        }
        assertFalse(testClothing1Counter == 0);
        assertFalse(testClothing1Counter == 1000);
        assertTrue(testClothing1UnavailableCounter == 0);
    }

    @Test
    void testSelectRandomShortBottomNoneAvailable() {
        testClothing1.setAvailability(false);
        testClothing2.setAvailability(false);
        testClothing3.setAvailability(false);
        testWardrobe.addShortBottom(testClothing1);
        testWardrobe.addShortBottom(testClothing2);
        testWardrobe.addShortBottom(testClothing3);
        int nullCounter = 0;
        for (int i = 0; i < 1000; i++) {
            if (testWardrobe.selectRandomShortBottom() == null) {
                nullCounter++;
            }
        }
        assertTrue(nullCounter == 1000);
    }

    // Toggling availability

    @Test
    void testToggleLongSleeveTopAvailabilityFirst() {
        testWardrobe.addLongSleeveTop(testClothing1);
        testWardrobe.addLongSleeveTop(testClothing2);
        testWardrobe.addLongSleeveTop(testClothing3);
        assertEquals("Long Sleeve Tops has been toggled from Available to Unavailable.", 
                testWardrobe.toggleClothingAvailability("testClothing1"));
        assertFalse(testWardrobe.getLongSleeveTops().get(0).getAvailability());
    }

    @Test
    void testToggleLongSleeveTopAvailabilityLast() {
        testWardrobe.addLongSleeveTop(testClothing1);
        testWardrobe.addLongSleeveTop(testClothing2);
        testWardrobe.addLongSleeveTop(testClothing3);
        assertEquals("Long Sleeve Tops has been toggled from Available to Unavailable.", 
                testWardrobe.toggleClothingAvailability("testClothing3"));
        assertFalse(testWardrobe.getLongSleeveTops().get(2).getAvailability());
    }

    @Test
    void testToggleLongSleeveTopAvailabilityUnavailable() {
        testClothing1.setAvailability(false);
        testWardrobe.addLongSleeveTop(testClothing1);
        testWardrobe.addLongSleeveTop(testClothing2);
        testWardrobe.addLongSleeveTop(testClothing3);
        assertEquals("Long Sleeve Tops has been toggled from Unavailable to Available.", 
                testWardrobe.toggleClothingAvailability("testClothing1"));
        assertTrue(testWardrobe.getLongSleeveTops().get(0).getAvailability());
    }

    @Test
    void testToggleLongSleeveTopAvailabilityDoesNotContain() {
        testWardrobe.addLongSleeveTop(testClothing1);
        testWardrobe.addLongSleeveTop(testClothing2);
        testWardrobe.addLongSleeveTop(testClothing3);
        assertEquals("", testWardrobe.toggleClothingAvailability("testClothing4"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2, testClothing3)), 
                testWardrobe.getLongSleeveTops());
    }

    @Test
    void testToggleShortSleeveTopAvailabilityFirst() {
        testWardrobe.addShortSleeveTop(testClothing1);
        testWardrobe.addShortSleeveTop(testClothing2);
        testWardrobe.addShortSleeveTop(testClothing3);
        assertEquals("Short Sleeve Tops has been toggled from Available to Unavailable.", 
                testWardrobe.toggleClothingAvailability("testClothing1"));
        assertFalse(testWardrobe.getShortSleeveTops().get(0).getAvailability());
    }

    @Test
    void testToggleShortSleeveTopAvailabilityLast() {
        testWardrobe.addShortSleeveTop(testClothing1);
        testWardrobe.addShortSleeveTop(testClothing2);
        testWardrobe.addShortSleeveTop(testClothing3);
        assertEquals("Short Sleeve Tops has been toggled from Available to Unavailable.", 
                testWardrobe.toggleClothingAvailability("testClothing3"));
        assertFalse(testWardrobe.getShortSleeveTops().get(2).getAvailability());
    }

    @Test
    void testToggleShortSleeveTopAvailabilityUnavailable() {
        testClothing1.setAvailability(false);
        testWardrobe.addShortSleeveTop(testClothing1);
        testWardrobe.addShortSleeveTop(testClothing2);
        testWardrobe.addShortSleeveTop(testClothing3);
        assertEquals("Short Sleeve Tops has been toggled from Unavailable to Available.", 
                testWardrobe.toggleClothingAvailability("testClothing1"));
        assertTrue(testWardrobe.getShortSleeveTops().get(0).getAvailability());
    }

    @Test
    void testToggleShortSleeveTopAvailabilityDoesNotContain() {
        testWardrobe.addShortSleeveTop(testClothing1);
        testWardrobe.addShortSleeveTop(testClothing2);
        testWardrobe.addShortSleeveTop(testClothing3);
        assertEquals("", testWardrobe.toggleClothingAvailability("testClothing4"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2, testClothing3)), 
                testWardrobe.getShortSleeveTops());
    }

    @Test
    void testToggleLongBottomAvailabilityFirst() {
        testWardrobe.addLongBottom(testClothing1);
        testWardrobe.addLongBottom(testClothing2);
        testWardrobe.addLongBottom(testClothing3);
        assertEquals("Long Bottoms has been toggled from Available to Unavailable.", 
                testWardrobe.toggleClothingAvailability("testClothing1"));
        assertFalse(testWardrobe.getLongBottoms().get(0).getAvailability());
    }

    @Test
    void testToggleLongBottomAvailabilityLast() {
        testWardrobe.addLongBottom(testClothing1);
        testWardrobe.addLongBottom(testClothing2);
        testWardrobe.addLongBottom(testClothing3);
        assertEquals("Long Bottoms has been toggled from Available to Unavailable.", 
                testWardrobe.toggleClothingAvailability("testClothing3"));
        assertFalse(testWardrobe.getLongBottoms().get(2).getAvailability());
    }

    @Test
    void testToggleLongBottomAvailabilityUnavailable() {
        testClothing1.setAvailability(false);
        testWardrobe.addLongBottom(testClothing1);
        testWardrobe.addLongBottom(testClothing2);
        testWardrobe.addLongBottom(testClothing3);
        assertEquals("Long Bottoms has been toggled from Unavailable to Available.", 
                testWardrobe.toggleClothingAvailability("testClothing1"));
        assertTrue(testWardrobe.getLongBottoms().get(0).getAvailability());
    }

    @Test
    void testToggleLongBottomAvailabilityDoesNotContain() {
        testWardrobe.addLongBottom(testClothing1);
        testWardrobe.addLongBottom(testClothing2);
        testWardrobe.addLongBottom(testClothing3);
        assertEquals("", testWardrobe.toggleClothingAvailability("testClothing4"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2, testClothing3)), 
                testWardrobe.getLongBottoms());
    }

    @Test
    void testToggleShortBottomAvailabilityFirst() {
        testWardrobe.addShortBottom(testClothing1);
        testWardrobe.addShortBottom(testClothing2);
        testWardrobe.addShortBottom(testClothing3);
        assertEquals("Short Bottoms has been toggled from Available to Unavailable.", 
                testWardrobe.toggleClothingAvailability("testClothing1"));
        assertFalse(testWardrobe.getShortBottoms().get(0).getAvailability());
    }

    @Test
    void testToggleShortBottomAvailabilityLast() {
        testWardrobe.addShortBottom(testClothing1);
        testWardrobe.addShortBottom(testClothing2);
        testWardrobe.addShortBottom(testClothing3);
        assertEquals("Short Bottoms has been toggled from Available to Unavailable.", 
                testWardrobe.toggleClothingAvailability("testClothing3"));
        assertFalse(testWardrobe.getShortBottoms().get(2).getAvailability());
    }

    @Test
    void testToggleShortBottomAvailabilityUnavailable() {
        testClothing1.setAvailability(false);
        testWardrobe.addShortBottom(testClothing1);
        testWardrobe.addShortBottom(testClothing2);
        testWardrobe.addShortBottom(testClothing3);
        assertEquals("Short Bottoms has been toggled from Unavailable to Available.", 
                testWardrobe.toggleClothingAvailability("testClothing1"));
        assertTrue(testWardrobe.getShortBottoms().get(0).getAvailability());
    }

    @Test
    void testToggleShortBottomAvailabilityDoesNotContain() {
        testWardrobe.addShortBottom(testClothing1);
        testWardrobe.addShortBottom(testClothing2);
        testWardrobe.addShortBottom(testClothing3);
        assertEquals("", testWardrobe.toggleClothingAvailability("testClothing4"));
        assertEquals(new ArrayList<Clothing>(Arrays.asList(testClothing1, testClothing2, testClothing3)), 
                testWardrobe.getShortBottoms());
    }

}