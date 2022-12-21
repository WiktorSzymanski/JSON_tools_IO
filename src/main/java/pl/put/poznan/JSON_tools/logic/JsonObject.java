package pl.put.poznan.JSON_tools.logic;


import org.json.JSONObject;

public class JsonObject extends JSONObject {

    public JsonObject(String string) {
        super(string);
    }

    public JsonObject(){}
    public String getJSON() {
        return this.toString(2);
    }
}
