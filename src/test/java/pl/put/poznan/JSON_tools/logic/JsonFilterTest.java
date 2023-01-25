package pl.put.poznan.JSON_tools.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonFilterTest {

    @Test
    void testFiltering() {
        JsonObject json = new JsonObject("{one: two, key: value}");
        JsonFilter jf = new JsonFilter(json, Arrays.asList("key"));
        assertEquals("[{\"key\":\"value\"}]", jf.getJSON());
    }

    @Test
    void testFiltering2() {
        JsonObject json = new JsonObject("{one: two, key: value}");
        JsonFilter jf = new JsonFilter(json, Arrays.asList("key", "one"));
        assertEquals("[{\"one\":\"two\"}, {\"key\":\"value\"}]", jf.getJSON());

    }

    @Test
    void testFiltering3() {
        JsonObject json = new JsonObject("{one: two, key: value}");
        JsonFilter jf = new JsonFilter(json, Arrays.asList(""));
        assertEquals("[]", jf.getJSON());
    }

    @Test
    void testFiltering4() {
        JsonObject json = new JsonObject("{one: { key: value2 }, key: value}");
        JsonFilter jf = new JsonFilter(json, Arrays.asList("key"));
        assertEquals("[{\"key\":\"value2\"}, {\"key\":\"value\"}]", jf.getJSON());
    }

    @Test
    void testFiltering5() {
        JsonObject json = new JsonObject("{one: { key: value2 }, key: value}");
        JsonFilter jf = new JsonFilter(json, Arrays.asList("one"));
        assertEquals("[]", jf.getJSON());

    }
}