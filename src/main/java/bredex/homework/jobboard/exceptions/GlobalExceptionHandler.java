package bredex.homework.jobboard.exceptions;

import bredex.homework.jobboard.domain.InvalidClientNameException;
import bredex.homework.jobboard.domain.InvalidEmailException;
import bredex.homework.jobboard.domain.InvalidPositionLocationException;
import bredex.homework.jobboard.domain.InvalidPositionNameException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
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
    public ResponseEntity<Object> handleInvalidEmailEx(InvalidEmailException ex, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(("timestamp"), LocalDateTime.now());
        body.put(("status"), HttpStatus.BAD_REQUEST);
        body.put(("exception"), InvalidEmailException.class);
        body.put(("message"), ex.getMessage());
        body.put(("path"), webRequest.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public ResponseEntity<Object> handleInvalidClientNameEx(InvalidClientNameException ex, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(("timestamp"), LocalDateTime.now());
        body.put(("status"), HttpStatus.BAD_REQUEST);
        body.put(("exception"), InvalidClientNameException.class);
        body.put(("message"), ex.getMessage());
        body.put(("path"), webRequest.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public ResponseEntity<Object> handleInvalidPositionNameEx(InvalidPositionNameException ex, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(("timestamp"), LocalDateTime.now());
        body.put(("status"), HttpStatus.BAD_REQUEST);
        body.put(("exception"), InvalidPositionNameException.class);
        body.put(("message"), ex.getMessage());
        body.put(("path"), webRequest.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public ResponseEntity<Object> handleInvalidPositionLocationEx(InvalidPositionLocationException ex, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(("timestamp"), LocalDateTime.now());
        body.put(("status"), HttpStatus.BAD_REQUEST);
        body.put(("exception"), InvalidPositionLocationException.class);
        body.put(("message"), ex.getMessage());
        body.put(("path"), webRequest.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public ResponseEntity<Object> handleInvalidAPIKeyEx(InvalidAPIKeyException ex, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(("timestamp"), LocalDateTime.now());
        body.put(("status"), HttpStatus.UNAUTHORIZED);
        body.put(("exception"), InvalidAPIKeyException.class);
        body.put(("message"), ex.getMessage());
        body.put(("path"), webRequest.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler
    public ResponseEntity<Object> handleNoSuchPositionEx(NoSuchPositionException ex, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(("timestamp"), LocalDateTime.now());
        body.put(("status"), HttpStatus.NO_CONTENT);
        body.put(("exception"), NoSuchPositionException.class);
        body.put(("message"), ex.getMessage());
        body.put(("path"), webRequest.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);

    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                          HttpHeaders headers, HttpStatus status,
                                                                          WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), headers, status);
    }
}
