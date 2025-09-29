package io.github.williamandradesantana.libraryapi.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ResponseError(int status, String message, List<MyFieldError> errors) {

    public static ResponseError defaultError(String message) {
        return new ResponseError(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

    public static ResponseError conflictError(String message) {
        return new ResponseError(HttpStatus.CONFLICT.value(), message, List.of());
    }
}
