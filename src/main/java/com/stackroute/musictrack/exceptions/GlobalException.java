package com.stackroute.musictrack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice is an annotation provided by Spring allowing you to write global code that can be applied to a wide
// range of controllers — varying from all controllers to a chosen package or even a specific annotation.

@ControllerAdvice
public class GlobalException  {

    @ExceptionHandler(value = TrackNotFoundException.class)
    public ResponseEntity<Object> exception(TrackNotFoundException exception){
        return new ResponseEntity<>("Track not found", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = TrackAlreadyExistsException.class)
    public ResponseEntity<Object> exception(TrackAlreadyExistsException exception){
        return new ResponseEntity<>("Track already exists",HttpStatus.CONFLICT);
    }
}
