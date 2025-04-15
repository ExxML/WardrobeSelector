package persistence;

import model.Clothing;
import model.Wardrobe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

public class JsonWriterTest {

    // The following method was modeled on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Test
    public void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // Expected
        }
    }

    @Test
    public void testWriterEmptyWardrobe() {
        try {
            Wardrobe wardrobe = new Wardrobe();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWardrobe.json");
            writer.open();
            writer.write(wardrobe);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWardrobe.json");
            wardrobe = reader.read();
            assertEquals(new ArrayList<Clothing>(), wardrobe.getLongSleeveTops());
            assertEquals(new ArrayList<Clothing>(), wardrobe.getShortSleeveTops());
            assertEquals(new ArrayList<Clothing>(), wardrobe.getLongBottoms());
            assertEquals(new ArrayList<Clothing>(), wardrobe.getShortBottoms());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterRegularWardrobe() {
        try {
            Wardrobe wardrobe = new Wardrobe();
            wardrobe.addLongSleeveTop(new Clothing("a"));
            wardrobe.addShortSleeveTop(new Clothing("b"));
            wardrobe.addLongBottom(new Clothing("c"));
            wardrobe.addShortBottom(new Clothing("d"));
            JsonWriter writer = new JsonWriter("./data/testWriterRegularWardrobe.json");
            writer.open();
            writer.write(wardrobe);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterRegularWardrobe.json");
            wardrobe = reader.read();
            assertEquals(1, wardrobe.getLongSleeveTops().size());
            assertEquals(1, wardrobe.getShortSleeveTops().size());
            assertEquals(1, wardrobe.getLongBottoms().size());
            assertEquals(1, wardrobe.getShortBottoms().size());
            checkClothing("a", true, wardrobe.getLongSleeveTops().get(0));
            checkClothing("b", true, wardrobe.getShortSleeveTops().get(0));
            checkClothing("c", true, wardrobe.getLongBottoms().get(0));
            checkClothing("d", true, wardrobe.getShortBottoms().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
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