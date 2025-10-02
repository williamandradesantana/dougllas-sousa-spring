package io.github.williamandradesantana.libraryapi.controller;

import io.github.williamandradesantana.libraryapi.controller.dto.BookRegisterDTO;
import io.github.williamandradesantana.libraryapi.controller.dto.ResponseError;
import io.github.williamandradesantana.libraryapi.controller.mappers.BookMapper;
import io.github.williamandradesantana.libraryapi.exceptions.DuplicateRegisterException;
import io.github.williamandradesantana.libraryapi.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody BookRegisterDTO dto) {
        try {
            var book = bookMapper.toEntity(dto);
            bookService.create(book);

            // criar url para acesso dos dados do livro
            // retornar código created com header location
            return ResponseEntity.ok().body(book);
        } catch (DuplicateRegisterException e) {
            var error = ResponseError.conflictError("ISBN duplicated!");
            return ResponseEntity.status(error.status()).body(error.message());
        }

    }
}
