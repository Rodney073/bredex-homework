package bredex.homework.jobboard.domain;

public class InvalidClientNameException extends RuntimeException{

    public InvalidClientNameException(String message) {
        super(message);
    }
}
