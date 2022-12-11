package pl.put.poznan.JSON_tools.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.put.poznan.JSON_tools.logic.JsonTools;

@RestController( "SystemController" )
@RequestMapping( "/jsonToolsSystem" )
@CrossOrigin( origins = "*" )
public class JsonToolsController
{
    private static final Logger logger = LoggerFactory.getLogger( JsonToolsController.class );
    private final JsonTools jsonTools;

    @Autowired
    public JsonToolsController( JsonTools jsonTools )
    {
        this.jsonTools = jsonTools;
    }

    @PostMapping( "/minimizeJson" )
    public ResponseEntity< Object > minimizedJson( @RequestBody String jsonToConvert )
    {
        var minimizedJson = jsonTools.removeWhiteSpaces( jsonToConvert );
        logger.info( "Converting done!" );
        return new ResponseEntity<>( minimizedJson, HttpStatus.OK );

    }

    @PostMapping( "/beautifier" )
    public ResponseEntity< Object > beautifier( @RequestBody String jsonToConvert )
    {
        var response = jsonTools.beautifier( jsonToConvert );
        logger.info( "Converting done!" );
        return new ResponseEntity<>( response, HttpStatus.OK );
    }

    @PostMapping( "/sameResponse" )
    public ResponseEntity< Object > sendSameObjectInResponse( @RequestBody String json )
    {
        jsonTools.checkValidationOfJsonFormat( json );
        logger.info( "Received object!" );
        return new ResponseEntity<>( json, HttpStatus.OK );
    }
}
