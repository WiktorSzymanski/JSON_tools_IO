package pl.put.poznan.JSON_tools.rest;

import javax.validation.ValidationException;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;

import pl.put.poznan.JSON_tools.dto.MessageResponse;

/**
 * This class provides a consistent and understandable response for JSONExceptions, JsonProcessingExceptions, and ValidationExceptions.
 * <p>
 * The class is annotated with {@link RestControllerAdvice} to make it a global exception handler for the application.
 * It has a single method {@link #handleJsonProcessingException()} which is annotated with {@link ExceptionHandler}
 * and is capable of handling the mentioned exceptions.
 * </p>
 *
 */
@RestControllerAdvice
public class AdviceController
{
    /**
     * Handles JSONException, JsonProcessingException, and ValidationException.
     * <p>
     * This method returns a {@link ResponseEntity} object with a {@link MessageResponse} object as a body
     * and a {@link HttpStatus} of BAD_REQUEST.
     * </p>
     *
     * @return {@link ResponseEntity} with {@link MessageResponse} object and BAD_REQUEST status
     */
    @ExceptionHandler(
            { JSONException.class, JsonProcessingException.class, ValidationException.class } )
    public ResponseEntity< Object > handleJsonProcessingException()
    {
        return new ResponseEntity<>( new MessageResponse( "Wrong json input format!" ), HttpStatus.BAD_REQUEST );
    }
}