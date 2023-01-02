package pl.put.poznan.JSON_tools.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pl.put.poznan.JSON_tools.JsonToolsTestHelper;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as line brief description).
 */
class JsonStandardTest {

    @Nested
    class JsonStandard_Test {
        @Test
        void standard_shouldReturnCorrectWhenJsonFormatPassed()
        {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.CORRECT_MINIMIZED_JSON_EXAMPLE;
            var expectedResult = JsonToolsTestHelper.CORRECT_JSON_EXAMPLE;
            // WHEN
            var result = new JsonStandard(new JsonObject(exampleCorrectJson)).getJSON();
            // THEN
            assertEquals( expectedResult, result );
        }
    }
}