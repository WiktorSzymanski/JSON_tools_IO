package pl.put.poznan.JSON_tools.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ValidationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.put.poznan.JSON_tools.JsonToolsTestHelper;

/**
 * TestsFor {@link JsonTools}
 */
@SpringBootTest
class JsonToolsTest
{
    @Autowired
    JsonTools jsonTools;

    @BeforeEach
    void setUp()
    {

    }

    @Nested
    class JsonValidation
    {
        @Test
        void checkValidationOfJsonFormat_shouldReturnTrueWhenCorrectJsonFormatPassed()
        {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.CORRECT_JSON_EXAMPLE;
            var expectedResult = true;
            // WHEN
            var result = jsonTools.checkValidationOfJsonFormat( exampleCorrectJson );
            // THEN
            assertEquals( expectedResult, result );
        }

        @Test
        void checkValidationOfJsonFormat_shouldReturnTrueWhenMinimizedCorrectJsonFormatPassed()
        {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.CORRECT_MINIMIZED_JSON_EXAMPLE;
            var expectedResult = true;
            // WHEN
            var result = jsonTools.checkValidationOfJsonFormat( exampleCorrectJson );
            // THEN
            assertEquals( expectedResult, result );
        }

        @Test
        void checkValidationOfJsonFormat_shouldThrowExceptionWhenWrongFormatPassed()
        {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.WRONG_JSON_FORMAT_EXAMPLE;
            // WHEN
            // THEN
            assertThrows( ValidationException.class,
                () -> jsonTools.checkValidationOfJsonFormat( exampleCorrectJson ) );
        }
    }

    @Nested
    class JsonTools_minimize
    {
        @Test
        void removeWhiteSpaces_shouldReturnCorrectWhenJsonFormatPassed()
        {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.CORRECT_JSON_EXAMPLE;
            var expectedResult = JsonToolsTestHelper.CORRECT_MINIMIZED_JSON_EXAMPLE;
            // WHEN
            var result = jsonTools.removeWhiteSpaces( exampleCorrectJson );
            // THEN
            assertEquals( expectedResult, result );
        }

        @Test
        void removeWhiteSpaces_shouldThrowExceptionWhenWrongFormatPassed()
        {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.WRONG_JSON_FORMAT_EXAMPLE;
            // WHEN
            // THEN
            assertThrows( ValidationException.class,
                () -> jsonTools.removeWhiteSpaces( exampleCorrectJson ) );
        }
    }

    @Nested
    class JsonTools_beautifier
    {
        @Test
        void beautifier_shouldReturnCorrectWhenJsonFormatPassed()
        {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.CORRECT_MINIMIZED_JSON_EXAMPLE;
            var expectedResult = JsonToolsTestHelper.CORRECT_JSON_EXAMPLE;
            // WHEN
            var result = jsonTools.beautifier( exampleCorrectJson );
            // THEN
            assertEquals( expectedResult, result );
        }

        @Test
        void beautifier_shouldThrowExceptionWhenWrongFormatPassed()
        {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.WRONG_JSON_FORMAT_EXAMPLE;
            // WHEN
            // THEN
            assertThrows( ValidationException.class, () -> jsonTools.beautifier( exampleCorrectJson ) );
        }
    }
}
