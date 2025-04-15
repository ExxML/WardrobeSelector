package persistence;

import model.Clothing;
import model.Wardrobe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

public class JsonReaderTest {

    // The following method was modeled on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/NonExistentFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // Expected
        }
    }

    @Test
    public void testReaderEmptyWardrobe() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWardrobe.json");
        try {
            Wardrobe wardrobe = reader.read();
            assertEquals(new ArrayList<Clothing>(), wardrobe.getLongSleeveTops());
            assertEquals(new ArrayList<Clothing>(), wardrobe.getShortSleeveTops());
            assertEquals(new ArrayList<Clothing>(), wardrobe.getLongBottoms());
            assertEquals(new ArrayList<Clothing>(), wardrobe.getShortBottoms());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderRegularWardrobe() {
        JsonReader reader = new JsonReader("./data/testReaderRegularWardrobe.json");
        try {
            Wardrobe wardrobe = reader.read();
            ArrayList<Clothing> longSleeveTops = wardrobe.getLongSleeveTops();
            ArrayList<Clothing> shortSleeveTops = wardrobe.getShortSleeveTops();
            ArrayList<Clothing> longBottoms = wardrobe.getLongBottoms();
            ArrayList<Clothing> shortBottoms = wardrobe.getShortBottoms();
            assertEquals(1, longSleeveTops.size());
            assertEquals(1, shortSleeveTops.size());
            assertEquals(1, longBottoms.size());
            assertEquals(1, shortBottoms.size());
            checkClothing("a", true, longSleeveTops.get(0));
            checkClothing("b", true, shortSleeveTops.get(0));
            checkClothing("c", true, longBottoms.get(0));
            checkClothing("d", true, shortBottoms.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    /*
     * EFFECTS: checks to ensure the name and availability match for the given clothing
     */
    private void checkClothing(String name, boolean availability, Clothing clothing) {
        assertEquals(name, clothing.getClothingName());
        assertEquals(availability, clothing.getAvailability());
    }

}