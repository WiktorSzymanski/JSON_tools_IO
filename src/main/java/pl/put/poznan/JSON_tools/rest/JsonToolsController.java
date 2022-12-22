package pl.put.poznan.JSON_tools.rest;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.put.poznan.JSON_tools.logic.JsonFilter;
import pl.put.poznan.JSON_tools.logic.JsonMinificator;
import pl.put.poznan.JSON_tools.logic.JsonObject;
import pl.put.poznan.JSON_tools.logic.JsonStandard;

import javax.validation.ValidationException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController( "SystemController" )
@RequestMapping( "/jsonToolsSystem" )
@CrossOrigin( origins = "*" )
public class JsonToolsController
{
    private static final Logger logger = LoggerFactory.getLogger( JsonToolsController.class );

    @PostMapping( "/minimizeJson" )
    public ResponseEntity< Object > minimizedJson( @RequestBody String jsonToConvert )
    {
        JsonObject minimizedJson = new JsonMinificator(createAndValidateJson(jsonToConvert));
        logger.info( "Converting done!" );
        return new ResponseEntity<>( minimizedJson.getJSON(), HttpStatus.OK );

    }

    @PostMapping( "/beautifier" )
    public ResponseEntity< Object > beautifier( @RequestBody String jsonToConvert )
    {
        JsonObject standardisedJson = new JsonStandard(createAndValidateJson(jsonToConvert));
        logger.info( "Converting done!" );
        return new ResponseEntity<>( standardisedJson.getJSON(), HttpStatus.OK );
    }

    @PostMapping( "/filter" )
    public ResponseEntity< Object > filterJson(@RequestBody String jsonToConvert, @RequestParam List<String> param)
    {
        JsonObject filterJson = new JsonFilter(createAndValidateJson(jsonToConvert), param);
        logger.info( "Create object to filter!" );
        return new ResponseEntity<>( filterJson.getJSON(), HttpStatus.OK );

    }
    @PostMapping( "/sameResponse" )
    public ResponseEntity< Object > sendSameObjectInResponse( @RequestBody String json )
    {
        JsonObject jsonObject = createAndValidateJson(json);
        logger.info( "Received object!" );
        return new ResponseEntity<>( jsonObject.getJSON(), HttpStatus.OK );
    }


    private JsonObject createAndValidateJson(String json) {
        try {
            return new JsonObject(json);
        } catch(JSONException e) {
            throw new ValidationException("Wrong format!");
        }
    }
}
