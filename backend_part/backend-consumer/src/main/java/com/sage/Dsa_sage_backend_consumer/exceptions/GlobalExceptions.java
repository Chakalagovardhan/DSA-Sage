package com.sage.Dsa_sage_backend_consumer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(AllExceptions.MailSendingError.class)
    public ResponseEntity<?> mailsendingError(Exception ex){
        return new ResponseEntity<>("unable to send mail to the user", HttpStatus.BAD_REQUEST);
    }
}
