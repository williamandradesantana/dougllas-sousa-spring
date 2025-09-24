package io.github.williamandradesantana.libraryapi.controller;

import io.github.williamandradesantana.libraryapi.controller.dto.AuthorDTO;
import io.github.williamandradesantana.libraryapi.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/")
    public ResponseEntity<Void> save(@RequestBody AuthorDTO author) {
        var entityAuthor = author.mapAuthor();
        authorService.save(entityAuthor);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entityAuthor.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
