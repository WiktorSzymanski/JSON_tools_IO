package pl.put.poznan.JSON_tools.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.put.poznan.JSON_tools.JsonToolsTestHelper;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as line brief description).
 */
class JsonMinificatorTest {

    @Nested
    class MinificationTest {
        @Test
        void removeWhiteSpaces_shouldReturnCorrectWhenJsonFormatPassed()
        {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.CORRECT_JSON_EXAMPLE;
            var expectedResult = JsonToolsTestHelper.CORRECT_MINIMIZED_JSON_EXAMPLE;
            // WHEN
            var jsonObject = new JsonMinificator(new JsonObject(exampleCorrectJson));
            var result = jsonObject.getJSON();
            // THEN
            assertEquals( expectedResult, result );
        }
    }
}