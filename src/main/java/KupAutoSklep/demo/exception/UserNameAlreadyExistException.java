package KupAutoSklep.demo.exception;

public class UserNameAlreadyExistException extends RuntimeException{
    public UserNameAlreadyExistException(String message) {
        super(message);
    }
}
