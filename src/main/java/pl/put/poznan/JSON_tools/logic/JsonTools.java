package pl.put.poznan.JSON_tools.logic;

import lombok.NoArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class JsonTools
{
    public void checkValidationOfJsonFormat(String json )
    {
        try
        {
            new JSONObject( json );
        }
        catch( JSONException e )
        {
            throw new JSONException( "" );
        }
    }
}
