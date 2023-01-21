package pl.put.poznan.JSON_tools.rest;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.put.poznan.JSON_tools.logic.*;

import javax.validation.ValidationException;
import java.util.List;

/**
 *  This class is a RestController that handles requests for JSON conversion.
 *  It is annotated with {@link RestController} and {@link RequestMapping} to map requests to specific methods.
 *  It also uses {@link CrossOrigin} annotation to allow all origins to access the endpoints.
 *  The class also has a {@link Logger} instance to log the events happening in the class.
 */
@RestController( "SystemController" )
@RequestMapping( "/jsonToolsSystem" )
@CrossOrigin( origins = "*" )
public class JsonToolsController
{
    private static final Logger logger = LoggerFactory.getLogger( JsonToolsController.class );

    /**
     * Minimizes the JSON passed as request body.
     * <p>
     * The method is mapped to the endpoint '/minimizeJson' with {@link PostMapping} and takes in a {@link String} as request body.
     * It creates an instance of {@link JsonMinificator} passing the json passed in request body,
     * and returns the minimized json as a {@link ResponseEntity} with {@link HttpStatus#OK}.
     * </p>
     *
     * @param jsonToConvert the json to minimize in the request body
     * @return {@link ResponseEntity} with minimized json and {@link HttpStatus#OK}
     */
    @PostMapping( "/minimizeJson" )
    public ResponseEntity< Object > minimizedJson( @RequestBody String jsonToConvert )
    {
        JsonObject minimizedJson = new JsonMinificator(createAndValidateJson(jsonToConvert));
        logger.info( "Converting done!" );
        return new ResponseEntity<>( minimizedJson.getJSON(), HttpStatus.OK );

    }

    /**
     * Beautifies the JSON passed as request body.
     * <p>
     * The method is mapped to the endpoint '/beautifier' with {@link PostMapping} and takes in a {@link String} as request body.
     * It creates an instance of {@link JsonMinificator} passing the json passed in request body
     * and returns the beautified json as a {@link ResponseEntity} with {@link HttpStatus#OK}.
     */

    @PostMapping( "/beautifier" )
    public ResponseEntity< Object > beautifier( @RequestBody String jsonToConvert )
    {
        JsonObject standardisedJson = new JsonStandard(createAndValidateJson(jsonToConvert));
        logger.info( "Converting done!" );
        return new ResponseEntity<>( standardisedJson.getJSON(), HttpStatus.OK );
    }

    /**
     * Filters the JSON passed as request body based on the request parameters.
     * <p>
     * The method is mapped to the endpoint '/filter' with {@link PostMapping} and takes in a {@link String} as request body
     * and a {@link List} of {@link String} as request parameter.
     * It creates an instance of {@link JsonFilter} passing the json passed in request body and the list of parameters,
     * and returns the filtered json as a {@link ResponseEntity} with {@link HttpStatus#OK}.
     * </p>
     *
     * @param jsonToConvert the json to filter in the request body
     * @param param the list of parameters used to filter the json
     * @return {@link ResponseEntity} with filtered json and {@link HttpStatus#OK}
     */
    @PostMapping( "/filter" )
    public ResponseEntity< Object > filterJson(@RequestBody String jsonToConvert, @RequestParam List<String> param)
    {
        JsonObject filterJson = new JsonFilter(createAndValidateJson(jsonToConvert), param);
        logger.info( "Create object to filter!" );
        return new ResponseEntity<>( filterJson.getJSON(), HttpStatus.OK );

    }

    /**
     * Sends the same JSON passed as request body in the response.
     * <p>
     * The method is mapped to the endpoint '/filter' with {@link PostMapping} and takes in a {@link String} as request body
     * and a {@link List} of {@link String} as request parameter.
     * It creates an instance of {@link JsonFilter} passing the json passed in request body and the list of parameters,
     * and returns the filtered json as a {@link ResponseEntity} with {@link HttpStatus#OK}.
     * </p>
     *
     * @param json the json to be sent in the response
     * @return {@link ResponseEntity} with the same json and {@link HttpStatus#OK}
     */
    @PostMapping( "/sameResponse" )
    public ResponseEntity< Object > sendSameObjectInResponse( @RequestBody String json )
    {
        JsonObject jsonObject = createAndValidateJson(json);
        logger.info( "Received object!" );
        return new ResponseEntity<>( jsonObject.getJSON(), HttpStatus.OK );
    }


    /**
     * Sends the same JSON passed as request body in the response.
     * <p>
     * The method is mapped to the endpoint '/rejectedFilter' with {@link PostMapping} and takes in a {@link String} as request body
     * and a {@link List} of {@link String} as request parameter.
     * It creates an instance of {@link JsonFilter} passing the json passed in request body and the list of parameters,
     * and returns the filtered json without object send as key in @param  a {@link ResponseEntity} with {@link HttpStatus#OK}.
     * </p>
     *
     * @param jsonToConvert the json to be sent in the response
     * @return {@link ResponseEntity} with the same json and {@link HttpStatus#OK}
     */
    @PostMapping( "/rejectingFilter" )

    public ResponseEntity< Object > rejectingFilterJson(@RequestBody String jsonToConvert, @RequestParam List<String> param)
    {
        JsonObject rejectingFilterJson = new JsonRejectingFilter(createAndValidateJson(jsonToConvert), param);
        logger.info( "Create object to rejecting filter!" );
        return new ResponseEntity<>( rejectingFilterJson.getJSON(), HttpStatus.OK );

    }

    /**
     * Crates and validates a JsonObject
     * This method is a static method that takes the string parameter "json"
     * to check if the structure of the JsonObject object has been entered correctly
     * <p>
     *    Creates and returns a JsonObject using the json string passed as the parameter.
     *    If there is a JSONException, it throws a ValidationException with the message "Wrong format!".
     * </p>
     *
     * @param json the json to be sent in the response
     * @return {@link JsonObject}
     */

    private JsonObject createAndValidateJson(String json) {
        try {
            return new JsonObject(json);
        } catch(JSONException e) {
            throw new ValidationException("Wrong format!");
        }
    }
}
