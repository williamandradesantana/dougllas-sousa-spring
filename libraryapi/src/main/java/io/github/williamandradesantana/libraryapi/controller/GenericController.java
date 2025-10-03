package io.github.williamandradesantana.libraryapi.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public interface GenericController {
    default URI createHeaderLocation(UUID id) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
                .buildAndExpand(id).toUri();
    }
}
