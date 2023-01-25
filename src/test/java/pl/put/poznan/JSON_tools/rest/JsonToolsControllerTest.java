package pl.put.poznan.JSON_tools.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.util.UriComponentsBuilder;
import pl.put.poznan.JSON_tools.JsonToolsTestHelper;
import pl.put.poznan.JSON_tools.dto.MessageResponse;

import javax.validation.ValidationException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test for {@link JsonToolsController}
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JsonToolsControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {
    }

    @Nested
    class JsonTools_minimize {
        @Test
        void minimize_shouldReturnCorrectWhenJsonFormatPassed() {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.CORRECT_JSON_EXAMPLE;
            var expectedResult = JsonToolsTestHelper.CORRECT_MINIMIZED_JSON_EXAMPLE;
            // WHEN
            ResponseEntity<String> responsePost =
                    testRestTemplate.exchange("/jsonToolsSystem/minimizeJson", HttpMethod.POST,
                            new HttpEntity<>(exampleCorrectJson), String.class);
            // THEN
            assertEquals(HttpStatus.OK, responsePost.getStatusCode());
            assertEquals(expectedResult, responsePost.getBody());
        }

        @Test
        void removeWhiteSpaces_shouldThrowExceptionWhenWrongFormatPassed() throws JsonProcessingException {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.WRONG_JSON_FORMAT_EXAMPLE;
            var expectedResult = new MessageResponse("Wrong json input format!");
            // WHEN
            ResponseEntity<String> responsePost =
                    testRestTemplate.exchange("/jsonToolsSystem/minimizeJson", HttpMethod.POST,
                            new HttpEntity<>(exampleCorrectJson), String.class);
            // THEN
            assertEquals(HttpStatus.BAD_REQUEST, responsePost.getStatusCode());
            assertEquals(objectMapper.writeValueAsString(expectedResult), responsePost.getBody());
        }

        @Test
        void mock_removeWhiteSpaces() {
            // GIVEN
            var restController = mock(JsonToolsController.class);
            // WHEN
            // THEN
            given(restController.minimizedJson(null)).willThrow(new ValidationException("Wrong format!"));
        }
    }

    @Nested
    class JsonTools_sameResponse {
        @Test
        void sameResponse_shouldReturnCorrectWhenJsonFormatPassed() {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.CORRECT_JSON_EXAMPLE;
            var expectedResult = JsonToolsTestHelper.CORRECT_JSON_EXAMPLE;
            // WHEN
            ResponseEntity<String> responsePost =
                    testRestTemplate.exchange("/jsonToolsSystem/sameResponse", HttpMethod.POST,
                            new HttpEntity<>(exampleCorrectJson), String.class);
            // THEN
            assertEquals(HttpStatus.OK, responsePost.getStatusCode());
            assertEquals(expectedResult, responsePost.getBody());
        }

        @Test
        void sameResponse_shouldThrowExceptionWhenWrongFormatPassed() throws JsonProcessingException {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.WRONG_JSON_FORMAT_EXAMPLE;
            var expectedResult = new MessageResponse("Wrong json input format!");
            // WHEN
            ResponseEntity<String> responsePost =
                    testRestTemplate.exchange("/jsonToolsSystem/sameResponse", HttpMethod.POST,
                            new HttpEntity<>(exampleCorrectJson), String.class);
            // THEN
            assertEquals(HttpStatus.BAD_REQUEST, responsePost.getStatusCode());
            assertEquals(objectMapper.writeValueAsString(expectedResult), responsePost.getBody());
        }

        @Test
        void sameResponse_mockTest() {
            // GIVEN
            var restController = mock(JsonToolsController.class);
            // WHEN
            // THEN
            given(restController.sendSameObjectInResponse(null)).willThrow(new ValidationException("Wrong format!"));
        }

        @Test
        void sameResponse_mockTest2() {
            // GIVEN
            var restController = mock(JsonToolsController.class);
            // WHEN
            restController.sendSameObjectInResponse("");
            restController.sendSameObjectInResponse("");
            restController.sendSameObjectInResponse("");
            // THEN
            verify(restController, times(3)).sendSameObjectInResponse(any(String.class));
        }
    }

    @Nested
    class JsonTools_beautifier {
        @Test
        void beautifier_shouldReturnCorrectWhenJsonFormatPassed() {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.CORRECT_MINIMIZED_JSON_EXAMPLE;
            var expectedResult = JsonToolsTestHelper.CORRECT_JSON_EXAMPLE;
            // WHEN
            ResponseEntity<String> responsePost = testRestTemplate.exchange("/jsonToolsSystem/beautifier",
                    HttpMethod.POST, new HttpEntity<>(exampleCorrectJson), String.class);
            // THEN
            assertEquals(HttpStatus.OK, responsePost.getStatusCode());
            assertEquals(expectedResult, responsePost.getBody());
        }

        @Test
        void beautifier_shouldThrowExceptionWhenWrongFormatPassed() throws JsonProcessingException {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.WRONG_JSON_FORMAT_EXAMPLE;
            var expectedResult = new MessageResponse("Wrong json input format!");
            // WHEN
            ResponseEntity<String> responsePost = testRestTemplate.exchange("/jsonToolsSystem/beautifier",
                    HttpMethod.POST, new HttpEntity<>(exampleCorrectJson), String.class);
            // THEN
            assertEquals(HttpStatus.BAD_REQUEST, responsePost.getStatusCode());
            assertEquals(objectMapper.writeValueAsString(expectedResult), responsePost.getBody());
        }

        @Test
        void sameResponse_mockTest() {
            // GIVEN
            var restController = mock(JsonToolsController.class);
            // WHEN
            // THEN
            given(restController.beautifier("")).willThrow(new ValidationException("Wrong format!"));
        }

        @Test
        void sameResponse_mockTest2() {
            // GIVEN
            var restController = mock(JsonToolsController.class);
            // WHEN
            restController.sendSameObjectInResponse("");
            restController.sendSameObjectInResponse("");
            restController.sendSameObjectInResponse("");
            // THEN
            verify(restController, times(3)).sendSameObjectInResponse(any(String.class));
        }
    }

    @Nested
    class JsonTools_filter {
        @Test
        void filter_shouldReturnCorrectWhenJsonFormatPassed() {
            // GIVEN
            var url = "/jsonToolsSystem/filter";
            var exampleCorrectJson = "{\"one\": \"two\", \"key\": \"value\"}";
            var expectedResult = "[{\"one\":\"two\"}]";
            List<String> requestBodyParams = List.of("one");
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                    .queryParam("param", requestBodyParams);
            // WHEN
            ResponseEntity<String> responsePost = testRestTemplate.exchange(builder.build().toUri(),
                    HttpMethod.POST, new HttpEntity<>(exampleCorrectJson), String.class);
            // THEN
            assertEquals(HttpStatus.OK, responsePost.getStatusCode());
            assertEquals(expectedResult, responsePost.getBody());
        }

        @Test
        void filter_shouldThrowExceptionWhenWrongJsonFormatPassed() throws JsonProcessingException {
            // GIVEN
            var url = "/jsonToolsSystem/filter";
            var exampleCorrectJson = JsonToolsTestHelper.WRONG_JSON_FORMAT_EXAMPLE;
            var expectedResult = new MessageResponse("Wrong json input format!");
            List<String> requestBodyParams = List.of("one");
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                    .queryParam("param", requestBodyParams);
            // WHEN
            ResponseEntity<String> responsePost = testRestTemplate.exchange(builder.build().toUri(),
                    HttpMethod.POST, new HttpEntity<>(exampleCorrectJson), String.class);
            // THEN
            assertEquals(HttpStatus.BAD_REQUEST, responsePost.getStatusCode());
            assertEquals(objectMapper.writeValueAsString(expectedResult), responsePost.getBody());
        }

        @Test
        void filter_mockTest() {
            // GIVEN
            var restController = mock(JsonToolsController.class);
            // WHEN
            // THEN
            given(restController.filterJson("", Collections.emptyList())).willThrow(new ValidationException("Wrong format!"));
        }

        @Test
        void sameResponse_mockTest2() {
            // GIVEN
            var restController = mock(JsonToolsController.class);
            // WHEN
            restController.filterJson("", Collections.emptyList());
            restController.filterJson("", Collections.emptyList());
            restController.filterJson("", Collections.emptyList());
            // THEN
            verify(restController, times(3)).filterJson(any(String.class), any(List.class));
        }
    }

    @Nested
    class JsonValidation {
        @Test
        void checkValidationOfJsonFormat_shouldThrowExceptionWhenWrongFormatPassed() {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.WRONG_JSON_FORMAT_EXAMPLE;
            // WHEN
            // THEN
            assertThrows(ValidationException.class,
                    () -> JsonToolsController.createAndValidateJson(exampleCorrectJson));
        }
    }

    @Nested
    class JsonTools_numericParser {
        @Test
        void parseToNumeric_shouldReturnCorrectWhenJsonFormatPassed() {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.CORRECT_JSON_EXAMPLE;
            var expectedResult = JsonToolsTestHelper.CORRECT_JSON_EXAMPLE;
            // WHEN
            ResponseEntity<String> responsePost =
                    testRestTemplate.exchange("/jsonToolsSystem/parseToNumeric", HttpMethod.POST,
                            new HttpEntity<>(exampleCorrectJson), String.class);
            // THEN
            assertEquals(HttpStatus.OK, responsePost.getStatusCode());
            assertEquals(expectedResult, responsePost.getBody());
        }

        @Test
        void parseToNumeric_shouldThrowExceptionWhenWrongFormatPassed() throws JsonProcessingException {
            // GIVEN
            var exampleCorrectJson = JsonToolsTestHelper.WRONG_JSON_FORMAT_EXAMPLE;
            var expectedResult = new MessageResponse("Wrong json input format!");
            // WHEN
            ResponseEntity<String> responsePost =
                    testRestTemplate.exchange("/jsonToolsSystem/parseToNumeric", HttpMethod.POST,
                            new HttpEntity<>(exampleCorrectJson), String.class);
            // THEN
            assertEquals(HttpStatus.BAD_REQUEST, responsePost.getStatusCode());
            assertEquals(objectMapper.writeValueAsString(expectedResult), responsePost.getBody());
        }

        @Test
        void parseToNumeric_mockTest() {
            // GIVEN
            var restController = mock(JsonToolsController.class);
            // WHEN
            // THEN
            given(restController.parseToNumericJson(null)).willThrow(new ValidationException("Wrong format!"));
        }

        @Test
        void parseToNumeric_mockTest2() {
            // GIVEN
            var restController = mock(JsonToolsController.class);
            // WHEN
            restController.parseToNumericJson("");
            restController.parseToNumericJson("");
            restController.parseToNumericJson("");
            // THEN
            verify(restController, times(3)).parseToNumericJson(any(String.class));
        }
    }

    @Nested
    class JsonTools_rejectingFilter {
        @Test
        void rejectingFilter_mockTest() {
            // GIVEN
            var restController = mock(JsonToolsController.class);
            // WHEN
            // THEN
            given(restController.rejectingFilterJson(null, List.of())).willThrow(new ValidationException("Wrong format!"));
        }

        @Test
        void rejectingFilter_mockTest2() {
            // GIVEN
            var restController = mock(JsonToolsController.class);
            // WHEN
            restController.rejectingFilterJson("", List.of());
            restController.rejectingFilterJson("", List.of());
            restController.rejectingFilterJson("", List.of());
            // THEN
            verify(restController, times(3)).rejectingFilterJson(any(String.class), any(List.class));
        }
    }
}