package com.axonactive.PersonalProject.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ ResponseException.class })
    public ResponseEntity<Object> handleAll (final ResponseException e, final WebRequest request){
        logger.error("error",e);
        return new ResponseEntity<>(e.getResponsebody(), new HttpHeaders(), e.getResponsebody().getHttpStatus());
    }
}
