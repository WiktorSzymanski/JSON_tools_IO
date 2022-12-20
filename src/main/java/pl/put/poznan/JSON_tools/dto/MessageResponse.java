package pl.put.poznan.JSON_tools.dto;

import java.util.Objects;

import lombok.Getter;

@Getter
public class MessageResponse
{
    private final String message;

    public MessageResponse( String aMessage )
    {
        Objects.requireNonNull( aMessage );
        message = aMessage;
    }
}