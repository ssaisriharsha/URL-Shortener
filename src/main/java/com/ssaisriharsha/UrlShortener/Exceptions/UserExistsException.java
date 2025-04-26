package com.ssaisriharsha.UrlShortener.Exceptions;

public class UserExistsException extends RuntimeException{
    public UserExistsException(String message) {
        super(message);
    }
}
