package io.github.williamandradesantana.libraryapi.controller.commom;

import io.github.williamandradesantana.libraryapi.controller.dto.MyFieldError;
import io.github.williamandradesantana.libraryapi.controller.dto.ResponseError;
import io.github.williamandradesantana.libraryapi.exceptions.AuthorHaveABookException;
import io.github.williamandradesantana.libraryapi.exceptions.DuplicateRegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<MyFieldError> list = fieldErrors.stream()
                .map(fe -> new MyFieldError(fe.getField(), fe.getDefaultMessage()))
                .toList();
        return new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error", list);
    }

    @ExceptionHandler(DuplicateRegisterException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError handleDuplicateRegisterException(DuplicateRegisterException e) {
        return ResponseError.conflictError(e.getMessage());
    }

    @ExceptionHandler(AuthorHaveABookException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleAuthorHaveABookException(AuthorHaveABookException e) {
        return ResponseError.defaultError(e.getMessage());
    }
}
