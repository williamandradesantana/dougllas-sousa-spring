package io.github.williamandradesantana.libraryapi.controller.dto;

import java.util.UUID;

public record ClientResponseDTO(UUID id, String clientId, String clientSecret, String redirectURI, String scope) {}
