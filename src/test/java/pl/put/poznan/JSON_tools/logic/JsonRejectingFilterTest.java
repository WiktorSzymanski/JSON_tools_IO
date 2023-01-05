package pl.put.poznan.JSON_tools.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class JsonRejectingFilterTest {
    @Test
    void testRejectingFiltering() {
        JsonObject json = new JsonObject("{one: two, key: value}");
        JsonRejectingFilter jrf = new JsonRejectingFilter(json, Arrays.asList("key"));
        assertEquals("{\"one\": \"two\"}", jrf.getJSON());
    }

    @Test
    void testFiltering2() {
        JsonObject json = new JsonObject("{one: two, key: value}");
        JsonRejectingFilter jrf = new JsonRejectingFilter(json, Arrays.asList("key", "one"));
        assertEquals("{}", jrf.getJSON());

    }

    @Test
    void testRejectingFiltering3() {
        JsonObject json = new JsonObject("{one: two, key: value}");
        JsonRejectingFilter jrf = new JsonRejectingFilter(json, Arrays.asList(""));
        assertEquals("{\n" +
                "    \"one\": \"two\",\n" +
                "    \"key\": \"value\"\n" +
                "}", jrf.getJSON());

    }
}