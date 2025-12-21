package io.github.williamandradesantana.libraryapi.controller.commom;

import io.github.williamandradesantana.libraryapi.controller.dto.MyFieldError;
import io.github.williamandradesantana.libraryapi.controller.dto.ResponseError;
import io.github.williamandradesantana.libraryapi.exceptions.AuthorHaveABookException;
import io.github.williamandradesantana.libraryapi.exceptions.DuplicateRegisterException;
import io.github.williamandradesantana.libraryapi.exceptions.InvalidFieldException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String MESSAGE_VALIDATION_ERROR = "Validation error";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Validation error: {}", e.getMessage());
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<MyFieldError> list = fieldErrors.stream()
                .map(fe -> new MyFieldError(fe.getField(), fe.getDefaultMessage()))
                .toList();
        return new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value(), MESSAGE_VALIDATION_ERROR, list);
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

    @ExceptionHandler(InvalidFieldException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseError handleInvalidFieldException(InvalidFieldException e) {
        return new ResponseError(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                MESSAGE_VALIDATION_ERROR,
                List.of(new MyFieldError(e.getField(), e.getMessage()))
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseError handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseError(HttpStatus.FORBIDDEN.value(), "Access denied!", List.of());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handleExceptionWithoutTreat(RuntimeException e) {
        log.error("Unexpected error: {}", e.getMessage());
        String message = e.getMessage();
        if (message != null) {
            message = message.split("\\n")[0];
        }
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, List.of());
    }
}
