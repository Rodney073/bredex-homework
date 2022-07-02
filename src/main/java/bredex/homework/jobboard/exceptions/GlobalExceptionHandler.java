package bredex.homework.jobboard.exceptions;

import bredex.homework.jobboard.domain.InvalidClientNameException;
import bredex.homework.jobboard.domain.InvalidEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleInvalidEmailException(InvalidEmailException ex, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(("timestamp"), LocalDateTime.now());
        body.put(("status"), HttpStatus.BAD_REQUEST.value());
        body.put(("exception"), InvalidEmailException.class);
        body.put(("message"), ex.getMessage());
        body.put(("path"), webRequest.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public ResponseEntity<Object> handleInvalidClientNameException(InvalidClientNameException ex, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(("timestamp"), LocalDateTime.now());
        body.put(("status"), HttpStatus.BAD_REQUEST.value());
        body.put(("exception"), InvalidClientNameException.class);
        body.put(("message"), ex.getMessage());
        body.put(("path"), webRequest.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

    }
}
