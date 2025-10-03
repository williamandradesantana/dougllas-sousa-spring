package io.github.williamandradesantana.libraryapi.controller;

import io.github.williamandradesantana.libraryapi.controller.dto.BookRegisterDTO;
import io.github.williamandradesantana.libraryapi.controller.mappers.BookMapper;
import io.github.williamandradesantana.libraryapi.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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
}
