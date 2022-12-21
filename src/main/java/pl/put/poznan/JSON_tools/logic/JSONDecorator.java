package pl.put.poznan.JSON_tools.logic;

import org.json.JSONObject;

public abstract class JSONDecorator extends JSONObject
{
    protected JSONObject jsonObject;

    public JSONDecorator( JSONObject jsonObject )
    {
        this.jsonObject = jsonObject;
    }

    public JSONObject getJSON()
    {
        return jsonObject;
    }

}