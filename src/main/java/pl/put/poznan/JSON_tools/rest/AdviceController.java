package pl.put.poznan.JSON_tools.rest;

import javax.validation.ValidationException;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;

import pl.put.poznan.JSON_tools.dto.MessageResponse;

@RestControllerAdvice
public class AdviceController
{
    @ExceptionHandler(
    { JSONException.class, JsonProcessingException.class, ValidationException.class } )
    public ResponseEntity< Object > handleJsonProcessingException()
    {
        return new ResponseEntity<>( new MessageResponse( "Wrong json input format!" ), HttpStatus.BAD_REQUEST );
    }
}
