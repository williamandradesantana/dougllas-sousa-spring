package io.github.williamandradesantana.libraryapi.controller;

import io.github.williamandradesantana.libraryapi.controller.dto.AuthorDTO;
import io.github.williamandradesantana.libraryapi.controller.mappers.AuthorMapper;
import io.github.williamandradesantana.libraryapi.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authors")
public class AuthorController implements GenericController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @PostMapping("/")
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Save", description = "Register new author")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Registered successfully!"),
            @ApiResponse(responseCode = "422", description = "Validation error!"),
            @ApiResponse(responseCode = "409", description = "Author already exists!")
    })
    public ResponseEntity<Object> save(@Valid @RequestBody AuthorDTO dto) {
        var entityAuthor = authorMapper.toEntity(dto);
        authorService.save(entityAuthor);

        URI location = createHeaderLocation(entityAuthor.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER', 'OPERATOR')")
    @Operation(summary = "Get one author", description = "Return details of an author")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Author found!"),
            @ApiResponse(responseCode = "404", description = "Author not found!")
    })
    public ResponseEntity<AuthorDTO> get(@PathVariable("id") String id) {
        var authorId = UUID.fromString(id);

        return authorService.get(authorId).map((a) -> {
            var dto = authorMapper.toDTO(a);
            return ResponseEntity.ok().body(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Delete one author")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Author excluded!"),
            @ApiResponse(responseCode = "404", description = "Author not found!"),
            @ApiResponse(responseCode = "400", description = "Cannot delete author because they have registered books.")
    })
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
    @Operation(summary = "Search", description = "Perform an author search using specific parameters.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully!"),
    })
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
    @Operation(summary = "Update", description = "Update an author!")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated successfully!"),
            @ApiResponse(responseCode = "404", description = "Author not found!"),
            @ApiResponse(responseCode = "409", description = "Author already exists!")
    })
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
