package pl.put.poznan.JSON_tools.logic;

import org.json.JSONObject;

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
        this.jsonObject.keySet()
            .removeIf( key -> !this.keys.contains(key));
        return this.jsonObject.toString(4);
    }

}
