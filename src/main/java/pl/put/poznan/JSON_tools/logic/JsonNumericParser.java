package pl.put.poznan.JSON_tools.logic;

import org.json.JSONObject;

/**
 * Extends {@see JsonDecorator}. It makes JSON parse it's numeric strings as numeric values.
 */
public class JsonNumericParser extends JsonDecorator {
    /**
     * class constructor
     * @param jsonObject JsonObject class object
     */
    public JsonNumericParser(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Parsing numeric values. If value of a key can be parsed to
     * numeric value then old value is being overridden with new numeric value.
     * @param jsonObject JsonObject class object
     * @return JSON Object cast to String
     */
    private JsonObject parseValues(JsonObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            var value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                jsonObject.put(key, inDepth((JSONObject) value));
            } else if (value instanceof String) {
                try {
                    jsonObject.put(key, Double.parseDouble((String) value));
                } catch (NumberFormatException ignored) {}
            }
        }
        return jsonObject;
    }

    /**
     * Does the same as parseValues except it operates on JSONObject instead of JsonObject
     * @param jsonObject JSONObject class object
     * @return JSONObject cast to String
     */
    private JSONObject inDepth(JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            var value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                jsonObject.put(key, inDepth((JSONObject) value));
            } else if (value instanceof String) {
                try {
                    jsonObject.put(key, Double.parseDouble((String) value));
                } catch (NumberFormatException ignored) {}
            }
        }
        return jsonObject;
    }


    /**
     * Override method to return filtering JSON in string format
     */
    @Override
    public String getJSON() {
        return parseValues(this.jsonObject).getJSON();
    }
}
