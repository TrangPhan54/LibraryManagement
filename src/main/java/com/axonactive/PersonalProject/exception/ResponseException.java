package com.axonactive.PersonalProject.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class ResponseException extends RuntimeException{

    @Getter
    private Responsebody responsebody;
    public ResponseException (String messageKey, String message, HttpStatus httpStatus){
        this.responsebody = new Responsebody(messageKey,message,httpStatus);
    }

    @Getter
    public static class Responsebody {
        private String messageKey;
        private String message;
        private HttpStatus httpStatus;
        public Responsebody (String messageKey, String message, HttpStatus httpStatus){
            this.messageKey = messageKey;
            this.message = message;
            this.httpStatus = httpStatus;
        }
    }
}
