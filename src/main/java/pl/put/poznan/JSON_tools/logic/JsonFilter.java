package pl.put.poznan.JSON_tools.logic;

import org.json.JSONObject;

import java.util.List;

// Keys in list keys_in are returning with value. If list 'keys_in' is empty, return empty list
public class JsonFilter extends JsonDecorator
{
    private final List<String> keys;


    public JsonFilter(JsonObject jsonObject, List<String> keys) {
        super(jsonObject);
        this.keys = keys;
    }

    @Override
    public String getJSON()
    {
        JSONObject json = jsonObject;
        json.keySet()
            .removeIf( key -> !ifInclude( key ) );
        return json.toString();
    }

    private boolean ifInclude( String key )
    {

        for( String k : keys )
        {
            if( k.equals( key ) )
            {
                return true;
            }
        }
        return false;
    }
}
