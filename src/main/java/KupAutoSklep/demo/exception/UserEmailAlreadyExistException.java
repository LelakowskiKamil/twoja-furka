package KupAutoSklep.demo.exception;

public class UserEmailAlreadyExistException extends RuntimeException{
    public UserEmailAlreadyExistException(String message) {
        super(message);
    }
}
