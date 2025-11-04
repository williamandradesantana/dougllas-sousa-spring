package io.github.williamandradesantana.libraryapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ClientDTO(
        @NotBlank(message = "The field clientId is required!")
        @Length(max = 150, message = "Maximum length is 150 chars")
        String clientId,
        @NotBlank(message = "The field clientSecret is required!")
        @Length(max = 400, message = "Maximum length is 400 chars")
        String clientSecret,
        @NotBlank(message = "The field redirectURI is required!")
        @Length(max = 200, message = "Maximum length is 200 chars")
        String redirectURI,
        @Length(max = 50, message = "Maximum length is 50 chars")
        String scope
){}
