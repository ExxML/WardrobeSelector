package persistence;

import model.Clothing;
import model.Wardrobe;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads clothing types from JSON data stored in file
public class JsonReader {
    
    private String source;

    /*
     * The following method was modeled on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
     * EFFECTS: constructs reader to read from source file
     */
    public JsonReader(String source) {
        this.source = source;
    }

    /*
     * The following method was modeled on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
     * EFFECTS: reads clothing types from file and returns it;
     *                throws IOException if an error occurs reading data from file
     */
    public Wardrobe read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseClothingType(jsonObject);
    }

    /*
     * The following method was modeled on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
     * EFFECTS: reads source file as string and returns it
     */
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    /*
     * MODIFIES: wardrobe
     * EFFECTS: parse wardrobe from JSON object and returns it
     */
    private Wardrobe parseClothingType(JSONObject jsonObject) {
        Wardrobe wardrobe = new Wardrobe();
        JSONArray longSleeveTops = jsonObject.getJSONArray("Long Sleeve Tops");
        JSONArray shortSleeveTops = jsonObject.getJSONArray("Short Sleeve Tops");
        JSONArray longBottoms = jsonObject.getJSONArray("Long Bottoms");
        JSONArray shortBottoms = jsonObject.getJSONArray("Short Bottoms");
        for (Object json : longSleeveTops) {
            JSONObject clothing = (JSONObject) json;
            addLongSleeveTop(wardrobe, clothing);
        }
        for (Object json : shortSleeveTops) {
            JSONObject clothing = (JSONObject) json;
            addShortSleeveTop(wardrobe, clothing);
        }
        for (Object json : longBottoms) {
            JSONObject clothing = (JSONObject) json;
            addLongBottom(wardrobe, clothing);
        }
        for (Object json : shortBottoms) {
            JSONObject clothing = (JSONObject) json;
            addShortBottom(wardrobe, clothing);
        }
        return wardrobe;
    }

    /*
     * MODIFIES: wardrobe
     * EFFECTS: parses a clothing from JSON object and adds it to long sleeve tops
     */
    private void addLongSleeveTop(Wardrobe wardrobe, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        boolean availability = jsonObject.getBoolean("availability");
        Clothing clothing = new Clothing(name);
        clothing.setAvailability(availability);
        wardrobe.addLongSleeveTop(clothing);
    }

    /*
     * MODIFIES: wardrobe
     * EFFECTS: parses a clothing from JSON object and adds it to short sleeve tops
     */
    private void addShortSleeveTop(Wardrobe wardrobe, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        boolean availability = jsonObject.getBoolean("availability");
        Clothing clothing = new Clothing(name);
        clothing.setAvailability(availability);
        wardrobe.addShortSleeveTop(clothing);
    }

    /*
     * MODIFIES: wardrobe
     * EFFECTS: parses a clothing from JSON object and adds it to long bottoms
     */
    private void addLongBottom(Wardrobe wardrobe, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        boolean availability = jsonObject.getBoolean("availability");
        Clothing clothing = new Clothing(name);
        clothing.setAvailability(availability);
        wardrobe.addLongBottom(clothing);
    }

    /*
     * MODIFIES: wardrobe
     * EFFECTS: parses a clothing from JSON object and adds it to short bottoms
     */
    private void addShortBottom(Wardrobe wardrobe, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        boolean availability = jsonObject.getBoolean("availability");
        Clothing clothing = new Clothing(name);
        clothing.setAvailability(availability);
        wardrobe.addShortBottom(clothing);
    }

}