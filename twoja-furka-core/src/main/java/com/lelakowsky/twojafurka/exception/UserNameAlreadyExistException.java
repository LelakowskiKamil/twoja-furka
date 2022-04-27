package com.lelakowsky.twojafurka.exception;

public class UserNameAlreadyExistException extends RuntimeException{
    public UserNameAlreadyExistException(String message) {
        super(message);
    }
}
