package pl.put.poznan.JSON_tools.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication( scanBasePackages =
{ "pl.put.poznan" } )
public class JsonToolsApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run( JsonToolsApplication.class, args );
    }
}
