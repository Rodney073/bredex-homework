package bredex.homework.jobboard.domain;

public class InvalidPositionNameException extends RuntimeException {
    public InvalidPositionNameException(String message) {
        super(message);
    }
}
