package io.github.williamandradesantana.libraryapi.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserDTO(
        @NotBlank(message = "The field login is required!")
        String login,
        @Email(message = "The email is invalid!")
        String email,
        @NotBlank(message = "The field password is required!")
        String password,
        List<String> roles) {
}
