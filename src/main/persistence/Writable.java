package persistence;

import org.json.JSONObject;

// Represents an interface to return a JSON object
public interface Writable {

    /*
     * The following method was modeled on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
     * EFFECTS: returns this as JSON object
     */
    JSONObject toJson();

}
