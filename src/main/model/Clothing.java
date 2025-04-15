package model;

import org.json.JSONObject;

import model.event.Event;
import model.event.EventLog;
import persistence.Writable;

// Represents a piece of clothing that has a name and a toggleable availability, belonging to a clothing type
public class Clothing implements Writable {

    private String clothingName; // name of the piece of clothing
    private boolean availability; // availability of the piece of clothing (whether it can be selected)

    /*
     * REQUIRES: name has a non-zero length
     * EFFECTS: constructs a new piece of clothing with clothingName set to name and
     *                availability automatically set to true
     */
    public Clothing(String name) {
        clothingName = name;
        availability = true;
    }

    /*
     * MODIFIES: this
     * EFFECTS: toggles the availability of the piece of clothing
     */
    public void toggleAvailability() {
        availability = !availability;
    }

    // Getters

    public String getClothingName() {
        return clothingName;
    }

    public boolean getAvailability() {
        return availability;
    }

    // Setters

    public void setClothingName(String name) {
        this.clothingName = name;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
        EventLog.getInstance().logEvent(new Event(clothingName + " availability toggled to " 
                + this.availability + "."));
    }

    /*
     * EFFECTS: returns a JSON object of a piece of clothing with name and availability
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", clothingName);
        json.put("availability", availability);
        return json;
    }

}