package com.example.user.web.controller.Exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;


@ControllerAdvice
public class ExceptionHandlingController {

    //exception pour les codes non valid
    @ExceptionHandler({CodeNotValidException.class})
    public ResponseEntity<Object> handleCodeNotValidException(CodeNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[ Error 400 ] Code de permis validation : " + exception.getMessage());
    }

    //exception pour les corps de request avec des champs manquants
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[ Error 400 ] Bad Request : " + exception.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("[ Error 500 ] ResponseStatusException : " + exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("[ Error 500 ] ConstraintViolationException : " + exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("[ Error 500 ] RuntimeException : " + exception.getMessage());
    }
}
