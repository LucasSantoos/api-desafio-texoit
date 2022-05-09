package com.santos.texoit.services.enterprise;

import java.util.Optional;
import javax.xml.bind.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Validation> defaultException(Exception e) {
        Validation validation = Validation.builder().message(resolve(e)).build();
        return new ResponseEntity<>(validation, HttpStatus.BAD_REQUEST);
    }

    public static String resolve(Exception e) {
        if (e instanceof ValidationException) {
            return e.getMessage();
        }
        return Optional.ofNullable(e.getMessage()).orElse("Erro interno no servidor");
    }
}
