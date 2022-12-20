package pl.put.poznan.JSON_tools.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController( "SystemController" )
@RequestMapping( "/jsonToolsSystem" )
@CrossOrigin( origins = "*" )
public class JsonToolsController
{
    private static final Logger logger = LoggerFactory.getLogger( JsonToolsController.class );

    public JsonToolsController()
    {
    }

    @PostMapping( "/sameResponse" )
    public ResponseEntity< Object > sendSameObjectInResponse( @RequestBody String json )
    {
        logger.info( "Received object!" );
        return new ResponseEntity<>( json, HttpStatus.OK );
    }
}
