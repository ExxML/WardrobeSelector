package model;

import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import model.event.Event;
import model.event.EventLog;
import persistence.Writable;

// Represents the four clothing categories: Long Sleeve Tops, Short Sleeve Tops, Long Bottoms, and Short Bottoms
// which each store clothing
public class Wardrobe implements Writable {

    private ArrayList<Clothing> longSleeveTops; // a list of long sleeve tops clothing
    private ArrayList<Clothing> shortSleeveTops; // a list of short sleeve tops clothing
    private ArrayList<Clothing> longBottoms; // a list of long bottoms clothing
    private ArrayList<Clothing> shortBottoms; // a list of short bottoms clothing

    /*
     * EFFECTS: constructs four empty lists of Clothing for each category of clothing
     */
    public Wardrobe() {
        longSleeveTops = new ArrayList<Clothing>();
        shortSleeveTops = new ArrayList<Clothing>();
        longBottoms = new ArrayList<Clothing>();
        shortBottoms = new ArrayList<Clothing>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a piece of clothing to long sleeve tops category
     */
    public void addLongSleeveTop(Clothing clothing) {
        longSleeveTops.add(clothing);
        EventLog.getInstance().logEvent(new Event(clothing.getClothingName() + " added to Long Sleeve Tops."));
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a piece of clothing to short sleeve tops category
     */
    public void addShortSleeveTop(Clothing clothing) {
        shortSleeveTops.add(clothing);
        EventLog.getInstance().logEvent(new Event(clothing.getClothingName() + " added to Short Sleeve Tops."));
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a piece of clothing to long bottoms category
     */
    public void addLongBottom(Clothing clothing) {
        longBottoms.add(clothing);
        EventLog.getInstance().logEvent(new Event(clothing.getClothingName() + " added to Long Bottoms."));
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a piece of clothing to short bottoms category
     */
    public void addShortBottom(Clothing clothing) {
        shortBottoms.add(clothing);
        EventLog.getInstance().logEvent(new Event(clothing.getClothingName() + " added to Short Bottoms."));
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes the clothing matching the name
     *                if multiple clothes have the same name, the first piece of clothing found will be removed
     *                returns the name of the category that the removed clothing belonged to
     *                returns empty string if clothing was not found
     */
    public String removeClothing(String clothingName) {
        if (removeLongSleeveTop(clothingName)) {
            EventLog.getInstance().logEvent(new Event(clothingName + " removed from Long Sleeve Tops."));
            return "Long Sleeve Tops";
        } else if (removeShortSleeveTop(clothingName)) {
            EventLog.getInstance().logEvent(new Event(clothingName + " removed from Short Sleeve Tops."));
            return "Short Sleeve Tops";
        } else if (removeLongBottom(clothingName)) {
            EventLog.getInstance().logEvent(new Event(clothingName + " removed from Long Bottoms."));
            return "Long Bottoms";
        } else if (removeShortBottom(clothingName)) {
            EventLog.getInstance().logEvent(new Event(clothingName + " removed from Short Bottoms."));
            return "Short Bottoms";
        } else {
            return "";
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes a clothing from long sleeve tops category
     *                returns true if clothing was removed, false otherwise
     */
    private boolean removeLongSleeveTop(String clothingName) {
        for (int i = 0; i < longSleeveTops.size(); i++) {
            if (longSleeveTops.get(i).getClothingName().equals(clothingName)) {
                longSleeveTops.remove(i);
                return true;
            }
        }
        return false;
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes a clothing from short sleeve tops category
     *                returns true if clothing was removed, false otherwise
     */
    private boolean removeShortSleeveTop(String clothingName) {
        for (int i = 0; i < shortSleeveTops.size(); i++) {
            if (shortSleeveTops.get(i).getClothingName().equals(clothingName)) {
                shortSleeveTops.remove(i);
                return true;
            }
        }
        return false;
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes a clothing from long bottoms category
     *                returns true if clothing was removed, false otherwise
     */
    private boolean removeLongBottom(String clothingName) {
        for (int i = 0; i < longBottoms.size(); i++) {
            if (longBottoms.get(i).getClothingName().equals(clothingName)) {
                longBottoms.remove(i);
                return true;
            }
        }
        return false;
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes a clothing from short bottoms category
     *                returns true if clothing was removed, false otherwise
     */
    private boolean removeShortBottom(String clothingName) {
        for (int i = 0; i < shortBottoms.size(); i++) {
            if (shortBottoms.get(i).getClothingName().equals(clothingName)) {
                shortBottoms.remove(i);
                return true;
            }
        }
        return false;
    }

    /*
     * EFFECTS: return a random, available piece of clothing in long sleeve tops category
     *                return null if there are no available pieces of clothing in long sleeve tops category
     */
    public Clothing selectRandomLongSleeveTop() {
        ArrayList<Clothing> longSleeveTopsCopy = new ArrayList<Clothing>();
        longSleeveTopsCopy.addAll(longSleeveTops);
        Random rand;
        for (int i = 0; i < longSleeveTops.size(); i++) {
            rand = new Random();
            int randomIndex = rand.nextInt(longSleeveTopsCopy.size());
            Clothing randomLongSleeveTop = longSleeveTopsCopy.get(randomIndex);
            if (randomLongSleeveTop.getAvailability()) {
                return randomLongSleeveTop;
            } else {
                longSleeveTopsCopy.remove(randomIndex);
            }
        }
        return null;
    }

    /*
     * EFFECTS: return a random, available piece of clothing in short sleeve tops category
     *                return null if there are no available pieces of clothing in short sleeve tops category
     */
    public Clothing selectRandomShortSleeveTop() {
        ArrayList<Clothing> shortSleeveTopsCopy = new ArrayList<Clothing>();
        shortSleeveTopsCopy.addAll(shortSleeveTops);
        Random rand;
        for (int i = 0; i < shortSleeveTops.size(); i++) {
            rand = new Random();
            int randomIndex = rand.nextInt(shortSleeveTopsCopy.size());
            Clothing randomShortSleeveTop = shortSleeveTopsCopy.get(randomIndex);
            if (randomShortSleeveTop.getAvailability()) {
                return randomShortSleeveTop;
            } else {
                shortSleeveTopsCopy.remove(randomIndex);
            }
        }
        return null;
    }

    /*
     * EFFECTS: return a random, available piece of clothing in long bottoms category
     *                return null if there are no available pieces of clothing in long bottoms category
     */
    public Clothing selectRandomLongBottom() {
        ArrayList<Clothing> longBottomsCopy = new ArrayList<Clothing>();
        longBottomsCopy.addAll(longBottoms);
        Random rand;
        for (int i = 0; i < longBottoms.size(); i++) {
            rand = new Random();
            int randomIndex = rand.nextInt(longBottomsCopy.size());
            Clothing randomLongBottom = longBottomsCopy.get(randomIndex);
            if (randomLongBottom.getAvailability()) {
                return randomLongBottom;
            } else {
                longBottomsCopy.remove(randomIndex);
            }
        }
        return null;
    }

    /*
     * EFFECTS: return a random, available piece of clothing in short bottoms category
     *                return null if there are no available pieces of clothing in short bottoms category
     */
    public Clothing selectRandomShortBottom() {
        ArrayList<Clothing> shortBottomsCopy = new ArrayList<Clothing>();
        shortBottomsCopy.addAll(shortBottoms);
        Random rand;
        for (int i = 0; i < shortBottoms.size(); i++) {
            rand = new Random();
            int randomIndex = rand.nextInt(shortBottomsCopy.size());
            Clothing randomShortBottom = shortBottomsCopy.get(randomIndex);
            if (randomShortBottom.getAvailability()) {
                return randomShortBottom;
            } else {
                shortBottomsCopy.remove(randomIndex);
            }
        }
        return null;
    }

    // Legacy toggle availability methods (now uses GUI)

    /*
     * MODIFIES: this
     * EFFECTS: toggles availability of the clothing matching the name
     *                if multiple clothes have the same name, the first piece of clothing found will be toggled
     *                returns a message about the category of the toggled clothing and how the availability changed
     *                returns empty string if clothing was not found
     */
    public String toggleClothingAvailability(String clothingName) {
        String availableLongSleeveTop = toggleLongSleeveTopAvailability(clothingName);
        if (!availableLongSleeveTop.equals("")) {
            return availableLongSleeveTop;
        }
        String availableShortSleeveTop = toggleShortSleeveTopAvailability(clothingName);
        if (!availableShortSleeveTop.equals("")) {
            return availableShortSleeveTop;
        }
        String availableLongBottom = toggleLongBottomAvailability(clothingName);
        if (!availableLongBottom.equals("")) {
            return availableLongBottom;
        }
        String availableShortBottom = toggleShortBottomAvailability(clothingName);
        if (!availableShortBottom.equals("")) {
            return availableShortBottom;
        }
        return "";
    }

    /*
     * MODIFIES: this
     * EFFECTS: toggles availability of the clothing matching the name in long sleeve tops category
     *                returns a message about the clothing toggled, empty string otherwise
     */
    private String toggleLongSleeveTopAvailability(String clothingName) {
        for (int i = 0; i < longSleeveTops.size(); i++) {
            if (longSleeveTops.get(i).getClothingName().equals(clothingName)) {
                String initialAvailability;
                String finalAvailability;
                if (longSleeveTops.get(i).getAvailability()) {
                    initialAvailability = "Available";
                    finalAvailability = "Unavailable";
                } else {
                    initialAvailability = "Unavailable";
                    finalAvailability = "Available";
                }
                longSleeveTops.get(i).toggleAvailability();
                return "Long Sleeve Tops has been toggled from " + initialAvailability + " to " + finalAvailability 
                        + ".";
            }
        }
        return "";
    }

    /*
     * MODIFIES: this
     * EFFECTS: toggles availability of the clothing matching the name in short sleeve tops category
     *                returns a message about the clothing toggled, empty string otherwise
     */
    private String toggleShortSleeveTopAvailability(String clothingName) {
        for (int i = 0; i < shortSleeveTops.size(); i++) {
            if (shortSleeveTops.get(i).getClothingName().equals(clothingName)) {
                String initialAvailability;
                String finalAvailability;
                if (shortSleeveTops.get(i).getAvailability()) {
                    initialAvailability = "Available";
                    finalAvailability = "Unavailable";
                } else {
                    initialAvailability = "Unavailable";
                    finalAvailability = "Available";
                }
                shortSleeveTops.get(i).toggleAvailability();
                return "Short Sleeve Tops has been toggled from " + initialAvailability + " to " + finalAvailability 
                        + ".";
            }
        }
        return "";
    }

    /*
     * MODIFIES: this
     * EFFECTS: toggles availability of the clothing matching the name in long bottoms category
     *                returns a message about the clothing toggled, empty string otherwise
     */
    private String toggleLongBottomAvailability(String clothingName) {
        for (int i = 0; i < longBottoms.size(); i++) {
            if (longBottoms.get(i).getClothingName().equals(clothingName)) {
                String initialAvailability;
                String finalAvailability;
                if (longBottoms.get(i).getAvailability()) {
                    initialAvailability = "Available";
                    finalAvailability = "Unavailable";
                } else {
                    initialAvailability = "Unavailable";
                    finalAvailability = "Available";
                }
                longBottoms.get(i).toggleAvailability();
                return "Long Bottoms has been toggled from " + initialAvailability + " to " + finalAvailability + ".";
            }
        }
        return "";
    }

    /*
     * MODIFIES: this
     * EFFECTS: toggles availability of the clothing matching the name in short bottoms category
     *                returns a message about the clothing toggled, empty string otherwise
     */
    private String toggleShortBottomAvailability(String clothingName) {
        for (int i = 0; i < shortBottoms.size(); i++) {
            if (shortBottoms.get(i).getClothingName().equals(clothingName)) {
                String initialAvailability;
                String finalAvailability;
                if (shortBottoms.get(i).getAvailability()) {
                    initialAvailability = "Available";
                    finalAvailability = "Unavailable";
                } else {
                    initialAvailability = "Unavailable";
                    finalAvailability = "Available";
                }
                shortBottoms.get(i).toggleAvailability();
                return "Short Bottoms has been toggled from " + initialAvailability + " to " + finalAvailability + ".";
            }
        }
        return "";
    }

    // Getters

    public ArrayList<Clothing> getLongSleeveTops() {
        return longSleeveTops;
    }

    public ArrayList<Clothing> getShortSleeveTops() {
        return shortSleeveTops;
    }

    public ArrayList<Clothing> getLongBottoms() {
        return longBottoms;
    }

    public ArrayList<Clothing> getShortBottoms() {
        return shortBottoms;
    }
    
    /*
     * EFFECTS: returns a JSON object of the wardrobe and the clothing contained in each clothing type
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Long Sleeve Tops", longSleeveTopsToJson());
        json.put("Short Sleeve Tops", shortSleeveTopsToJson());
        json.put("Long Bottoms", longBottomsToJson());
        json.put("Short Bottoms", shortBottomsToJson());
        return json;
    }

    /*
     * EFFECTS: returns clothing in the long sleeve tops category as a JSON array
     */
    private JSONArray longSleeveTopsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Clothing c : longSleeveTops) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }

    /*
     * EFFECTS: returns clothing in the short sleeve tops category as a JSON array
     */
    private JSONArray shortSleeveTopsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Clothing c : shortSleeveTops) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }

    /*
     * EFFECTS: returns clothing in the long bottoms category as a JSON array
     */
    private JSONArray longBottomsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Clothing c : longBottoms) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }

    /*
     * EFFECTS: returns clothing in the short bottoms category as a JSON array
     */
    private JSONArray shortBottomsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Clothing c : shortBottoms) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }

}