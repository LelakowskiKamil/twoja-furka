package com.lelakowsky.twojafurka.exception;

public class UserEmailAlreadyExistException extends RuntimeException{
    public UserEmailAlreadyExistException(String message) {
        super(message);
    }
}
