package com.example.reservation.execption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlingController {

    //exception pour les codes non valid
    @ExceptionHandler({VehicleNotAvailable.class})
    public ResponseEntity<Object> handleCodeNotValidException(VehicleNotAvailable exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[ Error 666 ] Date non disponible : " + exception.getMessage());
    }
}
