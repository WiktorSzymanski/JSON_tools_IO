package pl.put.poznan.JSON_tools.logic;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Extends {@see JsonDecorator}.  It adds keys that store keys in List with String elements object to be return with values

*/
public class JsonFilter extends JsonDecorator
{

    private final List<String> keys;


    /**
     * class constructor
     * @param jsonObject JsonObject class object
     * @param keys list of keys to filter JSON object
     */
    public JsonFilter(JsonObject jsonObject, List<String> keys) {
        super(jsonObject);
        this.keys = keys;
    }
    /**
     * Overrideed method to return filtering JSON in string format
     */

    @Override
    public String getJSON()
    {
        /**
         * Filtering standard JSON input. If key from objects is contained in keys list from params,
         * then delete it from JSON
         * @return JSON Object cast to String
         */
        ArrayList<JsonObject> filteredJson= new ArrayList<JsonObject>();
        filterHelper(this.jsonObject, this.keys,  filteredJson);
        //this.jsonObject.keySet().removeIf( key -> !this.keys.contains(key));

        return filteredJson.toString();
    }

    /**
     * This method filterHelper filters the given JSONObject and adds the filtered key-value pairs to the ArrayList of JsonObjects.
     * @param obj JSONObject to be filtered
     * @param key List of keys to be filtered
     * @param filtered ArrayList of JsonObjects to hold the filtered key-value pairs
     */
    private static void filterHelper(JSONObject obj, List<String> key, ArrayList<JsonObject> filtered) {
        for (String k : obj.keySet()) {
            Object value = obj.get(k);
            if (value instanceof JSONObject) {
                filterHelper((JSONObject) value, key, filtered);
            } else if (value instanceof JSONArray) {
                filterArray((JSONArray) value, key, filtered);
                if (key.contains(k)){
                    filtered.add(new JsonObject("{"+k.toString()+":"+value.toString()+"}"));
                }
            } else if (key.contains(k)) {
                filtered.add(new JsonObject("{"+k.toString()+":"+value.toString()+"}"));
            }
        }
    }
    /**
    * This method filterArray filters the given JSONArray and adds the filtered key-value pairs to the ArrayList of JsonObjects.
    * @param array JSONArray to be filtered
    * @param key List of keys to be filtered
    * @param filtered ArrayList of JsonObjects to hold the filtered key-value pairs
    */
    private static void filterArray(JSONArray array, List<String> key, ArrayList<JsonObject> filtered) {
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONObject) {
                filterHelper((JSONObject) value, key, filtered);
            }
        }
    }

}
