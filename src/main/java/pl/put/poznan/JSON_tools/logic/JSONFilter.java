package pl.put.poznan.JSON_tools.logic;

import org.json.JSONObject;

import java.util.Arrays;
// Keys in list keys_in are returning with value. If list 'keys_in' is empty, return empty list
public class JSONFilter extends JSONDecorator {
    private final String[] keys;
    public JSONFilter(JSONObject jsonObject, String [] keys_in) {
        super(jsonObject);
        this.keys = keys_in;

    }
    @Override
    public JSONObject getJSON() {
        JSONObject json = jsonObject;
        json.keySet().removeIf(key -> !ifInclude(key));
        return json;
    }

    private boolean ifInclude(String key) {

        for (String k : keys) {
            if (k.equals(key)){
                return true;
            }
        }
        return false;
    }
};
