package pl.put.poznan.JSON_tools.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import pl.put.poznan.JSON_tools.logic.JSONFilter;

class JSONFilterTest
{

    @Test
    void testFiltering()
    {
        JSONObject json = new JSONObject( "{one: two, key: value}" );
        JSONFilter jf = new JSONFilter( json, new String[]
        { "key" } );
        assertEquals( "{\"key\":\"value\"}", jf.getJSON()
            .toString() );
    }

    @Test
    void testFiltering2()
    {
        JSONObject json = new JSONObject( "{one: two, key: value}" );
        JSONFilter jf = new JSONFilter( json, new String[]
        { "key", "one" } );
        assertEquals( "{\"one\":\"two\",\"key\":\"value\"}", jf.getJSON()
            .toString() );

    }

    @Test
    void testFiltering3()
    {
        JSONObject json = new JSONObject( "{one: two, key: value}" );
        JSONFilter jf = new JSONFilter( json, new String[]
        {} );
        assertEquals( "{}", jf.getJSON()
            .toString() );

    }
}