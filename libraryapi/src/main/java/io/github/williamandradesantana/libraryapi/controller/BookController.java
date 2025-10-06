package io.github.williamandradesantana.libraryapi.controller;

import io.github.williamandradesantana.libraryapi.controller.dto.BookRegisterDTO;
import io.github.williamandradesantana.libraryapi.controller.dto.SearchBookDTO;
import io.github.williamandradesantana.libraryapi.controller.mappers.BookMapper;
import io.github.williamandradesantana.libraryapi.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController implements GenericController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody BookRegisterDTO dto) {
        var book = bookMapper.toEntity(dto);
        bookService.create(book);
        URI location = createHeaderLocation(book.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SearchBookDTO> get(@PathVariable("id") String id) {
        var bookId = UUID.fromString(id);

        return bookService.get(bookId).map(book -> {
            var dto = bookMapper.toDTO(book);
            return ResponseEntity.ok().body(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        return bookService.get(UUID.fromString(id)).map(
                book -> {
                    bookService.delete(book);
                    return ResponseEntity.noContent().build();
                }
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
