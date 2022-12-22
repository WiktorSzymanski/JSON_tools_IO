package pl.put.poznan.JSON_tools.logic;


import org.json.JSONObject;

/**
 * Extension of class JSONObject that stores JSON as a String. It allows to access methods and fields it at any given moment.
 */
public class JsonObject extends JSONObject {

    /**
     * constructor with string pram
     */
    public JsonObject(String string) {
        super(string);
    }
    /**
     * constructor without params
     */
    public JsonObject(){}

    /**
     * Basic getter of JsonObject class
     */
    public String getJSON() {
        return this.toString(2);
    }
}
