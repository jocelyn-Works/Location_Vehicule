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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[ Error 666 ] Vehicle Not Available : " + exception.getMessage());
    }

    @ExceptionHandler({UserNotAvalaible.class})
    public ResponseEntity<Object> handleCodeNotValidException(UserNotAvalaible exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[ Error 666 ] User Not Available : " + exception.getMessage());
    }
}
