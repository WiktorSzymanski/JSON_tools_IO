package pl.put.poznan.JSON_tools.logic;

import java.util.List;

public class JsonRejectingFilter extends JsonDecorator{
    private final List<String> keys;

    /**
     * class constructor
     * @param jsonObject JsonObject class object
     * @param keys list of keys to filter JSON object
     */
    public JsonRejectingFilter(JsonObject jsonObject, List<String> keys) {
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
        this.jsonObject.keySet()
                .removeIf( key -> this.keys.contains(key));
        return this.jsonObject.toString(4);
    }
}
