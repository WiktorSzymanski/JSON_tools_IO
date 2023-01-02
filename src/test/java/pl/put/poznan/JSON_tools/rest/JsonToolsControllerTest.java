package pl.put.poznan.JSON_tools.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.util.UriComponentsBuilder;
import pl.put.poznan.JSON_tools.JsonToolsTestHelper;
import pl.put.poznan.JSON_tools.dto.MessageResponse;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test for {@link JsonToolsController}
 */
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class JsonToolsControllerTest
{
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp()
    {
    }

    @Nested
    class JsonTools_minimize
    {
        @Test
        void minimize_shouldReturnCorrectWhenJsonFormatPassed()
        {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.CORRECT_JSON_EXAMPLE;
            var expectedResult = JsonToolsTestHelper.CORRECT_MINIMIZED_JSON_EXAMPLE;
            // WHEN
            ResponseEntity< String > responsePost =
                testRestTemplate.exchange( "/jsonToolsSystem/minimizeJson", HttpMethod.POST,
                    new HttpEntity<>( exampleCorrectJson ), String.class );
            // THEN
            assertEquals( HttpStatus.OK, responsePost.getStatusCode() );
            assertEquals( expectedResult, responsePost.getBody() );
        }

        @Test
        void removeWhiteSpaces_shouldThrowExceptionWhenWrongFormatPassed() throws JsonProcessingException
        {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.WRONG_JSON_FORMAT_EXAMPLE;
            var expectedResult = new MessageResponse( "Wrong json input format!" );
            // WHEN
            ResponseEntity< String > responsePost =
                testRestTemplate.exchange( "/jsonToolsSystem/minimizeJson", HttpMethod.POST,
                    new HttpEntity<>( exampleCorrectJson ), String.class );
            // THEN
            assertEquals( HttpStatus.BAD_REQUEST, responsePost.getStatusCode() );
            assertEquals( objectMapper.writeValueAsString( expectedResult ), responsePost.getBody() );
        }
    }

    @Nested
    class JsonTools_sameResponse
    {
        @Test
        void sameResponse_shouldReturnCorrectWhenJsonFormatPassed()
        {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.CORRECT_JSON_EXAMPLE;
            var expectedResult = JsonToolsTestHelper.CORRECT_JSON_EXAMPLE;
            // WHEN
            ResponseEntity< String > responsePost =
                testRestTemplate.exchange( "/jsonToolsSystem/sameResponse", HttpMethod.POST,
                    new HttpEntity<>( exampleCorrectJson ), String.class );
            // THEN
            assertEquals( HttpStatus.OK, responsePost.getStatusCode() );
            assertEquals( expectedResult, responsePost.getBody() );
        }

        @Test
        void sameResponse_shouldThrowExceptionWhenWrongFormatPassed() throws JsonProcessingException
        {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.WRONG_JSON_FORMAT_EXAMPLE;
            var expectedResult = new MessageResponse( "Wrong json input format!" );
            // WHEN
            ResponseEntity< String > responsePost =
                testRestTemplate.exchange( "/jsonToolsSystem/sameResponse", HttpMethod.POST,
                    new HttpEntity<>( exampleCorrectJson ), String.class );
            // THEN
            assertEquals( HttpStatus.BAD_REQUEST, responsePost.getStatusCode() );
            assertEquals( objectMapper.writeValueAsString( expectedResult ), responsePost.getBody() );
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
            ResponseEntity< String > responsePost = testRestTemplate.exchange( "/jsonToolsSystem/beautifier",
                HttpMethod.POST, new HttpEntity<>( exampleCorrectJson ), String.class );
            // THEN
            assertEquals( HttpStatus.OK, responsePost.getStatusCode() );
            assertEquals( expectedResult, responsePost.getBody() );
        }

        @Test
        void beautifier_shouldThrowExceptionWhenWrongFormatPassed() throws JsonProcessingException
        {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.WRONG_JSON_FORMAT_EXAMPLE;
            var expectedResult = new MessageResponse( "Wrong json input format!" );
            // WHEN
            ResponseEntity< String > responsePost = testRestTemplate.exchange( "/jsonToolsSystem/beautifier",
                HttpMethod.POST, new HttpEntity<>( exampleCorrectJson ), String.class );
            // THEN
            assertEquals( HttpStatus.BAD_REQUEST, responsePost.getStatusCode() );
            assertEquals( objectMapper.writeValueAsString( expectedResult ), responsePost.getBody() );
        }
    }

    @Nested
    class JsonTools_filter
    {
        @Test
        void filter_shouldReturnCorrectWhenJsonFormatPassed()
        {
            // GIVEN
            var url = "/jsonToolsSystem/filter";
            var exampleCorrectJson = "{\"one\": \"two\", \"key\": \"value\"}";
            var expectedResult = "{\"one\": \"two\"}";
            List<String> requestBodyParams = List.of("one");
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                    .queryParam("param", requestBodyParams);
            // WHEN
            ResponseEntity< String > responsePost = testRestTemplate.exchange( builder.build().toUri(),
                    HttpMethod.POST, new HttpEntity<>( exampleCorrectJson ), String.class );
            // THEN
            assertEquals( HttpStatus.OK, responsePost.getStatusCode() );
            assertEquals( expectedResult, responsePost.getBody() );
        }

        @Test
        void filter_shouldThrowExceptionWhenWrongJsonFormatPassed() throws JsonProcessingException {
            // GIVEN
            var url = "/jsonToolsSystem/filter";
            var exampleCorrectJson = JsonToolsTestHelper.WRONG_JSON_FORMAT_EXAMPLE;
            var expectedResult = new MessageResponse( "Wrong json input format!" );
            List<String> requestBodyParams = List.of("one");
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                    .queryParam("param", requestBodyParams);
            // WHEN
            ResponseEntity< String > responsePost = testRestTemplate.exchange( builder.build().toUri(),
                    HttpMethod.POST, new HttpEntity<>( exampleCorrectJson ), String.class );
            // THEN
            assertEquals( HttpStatus.BAD_REQUEST, responsePost.getStatusCode() );
            assertEquals( objectMapper.writeValueAsString( expectedResult ), responsePost.getBody() );
        }
    }

    @Nested
    class JsonValidation
    {
        @Test
        void checkValidationOfJsonFormat_shouldThrowExceptionWhenWrongFormatPassed()
        {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.WRONG_JSON_FORMAT_EXAMPLE;
            // WHEN
            // THEN
            assertThrows( ValidationException.class,
                    () -> JsonToolsController.createAndValidateJson( exampleCorrectJson ) );
        }
    }
}