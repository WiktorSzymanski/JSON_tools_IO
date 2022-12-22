package pl.put.poznan.JSON_tools.logic;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


import java.util.Arrays;

class JsonFilterTest {

    @Test
    void testFiltering() {
        JsonObject json = new JsonObject("{one: two, key: value}");
        JsonFilter jf = new JsonFilter(json, Arrays.asList("key"));
        assertEquals("{\"key\": \"value\"}", jf.getJSON());
    }

    @Test
    void testFiltering2() {
        JsonObject json = new JsonObject("{one: two, key: value}");
        JsonFilter jf = new JsonFilter(json, Arrays.asList("key", "one"));
        assertEquals("{\n" +
                "    \"one\": \"two\",\n" +
                "    \"key\": \"value\"\n" +
                "}", jf.getJSON());

    }

    @Test
    void testFiltering3() {
        JsonObject json = new JsonObject("{one: two, key: value}");
        JsonFilter jf = new JsonFilter(json, Arrays.asList(""));
        assertEquals("{}", jf.getJSON());

    }
}