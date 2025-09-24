package io.github.williamandradesantana.libraryapi.controller;

import io.github.williamandradesantana.libraryapi.controller.dto.AuthorDTO;
import io.github.williamandradesantana.libraryapi.services.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> get(@PathVariable("id") String id) {
        var authorId = UUID.fromString(id);
        var author = authorService.get(authorId);

        if (author.isPresent()) {
            var entity = author.get();
            var authorDTO = new AuthorDTO(
                    entity.getId(), entity.getName(),
                    entity.getDateOfBirth(), entity.getNationality());
            return ResponseEntity.ok(authorDTO);
        }

        return ResponseEntity.notFound().build();
    }
}
