package pl.put.poznan.JSON_tools.logic;

import org.json.JSONObject;

public class JSONFilter extends JSONDecorator {
    private final String[] keys;
    public JSONFilter(JSONObject jsonObject, String[] keys) {
        super(jsonObject);
        this.keys = keys;
    }
    @Override
    public String getJSON() {
        JSONObject jsonObj = new JSONObject(this.jsonObject);
        jsonObj.keySet().removeIf(key -> !ifInclude(key));
        return jsonObj.toString();
    }

    private boolean ifInclude(String key){
        for (String k : keys){
            if (k.equals(key)){
                return true;
            }
        }
        return false;
    }
};
