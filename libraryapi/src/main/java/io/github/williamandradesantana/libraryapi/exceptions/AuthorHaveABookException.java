package io.github.williamandradesantana.libraryapi.exceptions;

public class AuthorHaveABookException extends RuntimeException {
    public AuthorHaveABookException(String message) {
        super(message);
    }
}
