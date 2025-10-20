package io.github.williamandradesantana.libraryapi.controller;

import io.github.williamandradesantana.libraryapi.controller.dto.AuthorDTO;
import io.github.williamandradesantana.libraryapi.controller.mappers.AuthorMapper;
import io.github.williamandradesantana.libraryapi.services.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController implements GenericController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @PostMapping("/")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Object> save(@Valid @RequestBody AuthorDTO dto) {
        var entityAuthor = authorMapper.toEntity(dto);
        authorService.save(entityAuthor);

        URI location = createHeaderLocation(entityAuthor.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER', 'OPERATOR')")
    public ResponseEntity<AuthorDTO> get(@PathVariable("id") String id) {
        var authorId = UUID.fromString(id);

        return authorService.get(authorId).map((a) -> {
            var dto = authorMapper.toDTO(a);
            return ResponseEntity.ok().body(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        var authorId = UUID.fromString(id);
        var author = authorService.get(authorId);

        if (author.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        authorService.delete(author.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('MANAGER', 'OPERATOR')")
    public ResponseEntity<List<AuthorDTO>> search(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nationality", required = false) String nationality
    ) {
        var authors = authorService.searchByExample(name, nationality);
        var authorsDTO = authors.stream().map(authorMapper::toDTO).toList();

        return ResponseEntity.ok().body(authorsDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Object> update(@Valid @PathVariable("id") String id, @RequestBody AuthorDTO dto) {
        var authorId = UUID.fromString(id);
        var optionalAuthor = authorService.get(authorId);

        if (optionalAuthor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var author = optionalAuthor.get();
        author.setName(dto.name());
        author.setDateOfBirth(dto.dateOfBirth());
        author.setNationality(dto.nationality());

        authorService.update(author);
        return ResponseEntity.noContent().build();
    }
}
