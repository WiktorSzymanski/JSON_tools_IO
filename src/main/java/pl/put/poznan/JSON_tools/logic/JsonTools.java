package pl.put.poznan.JSON_tools.logic;

import javax.validation.ValidationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Component( "jsonTools" )
public class JsonTools
{
    private final ObjectMapper myObjectMapper;

    public JsonTools()
    {
        DefaultPrettyPrinter p = new DefaultPrettyPrinter();
        DefaultPrettyPrinter.Indenter i = new DefaultIndenter( "  ", "\n" );
        this.myObjectMapper = new ObjectMapper();
        p.indentArraysWith( i );
        p.indentObjectsWith( i );
        myObjectMapper.setDefaultPrettyPrinter( p );
    }

    public boolean checkValidationOfJsonFormat( String json )
    {
        try
        {
            new JSONObject( json );
            return true;
        }
        catch( JSONException e )
        {
            throw new ValidationException( "" );
        }
    }

    public String removeWhiteSpaces( String aJsonToConvert )
    {
        checkValidationOfJsonFormat( aJsonToConvert );
        Gson gson = new GsonBuilder().create();
        JsonElement el = JsonParser.parseString( aJsonToConvert );
        return gson.toJson( el );
    }

    public String beautifier( String aJsonToConvert )
    {
        checkValidationOfJsonFormat( aJsonToConvert );
        try
        {
            Object object = myObjectMapper.readValue( aJsonToConvert, Object.class );
            return myObjectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString( object );
        }
        catch( JsonProcessingException aE )
        {
            throw new ValidationException( aE );
        }
    }
}
