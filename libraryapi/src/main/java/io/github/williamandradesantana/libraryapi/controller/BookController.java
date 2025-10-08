package io.github.williamandradesantana.libraryapi.controller;

import io.github.williamandradesantana.libraryapi.controller.dto.BookRegisterDTO;
import io.github.williamandradesantana.libraryapi.controller.dto.SearchBookDTO;
import io.github.williamandradesantana.libraryapi.controller.mappers.BookMapper;
import io.github.williamandradesantana.libraryapi.model.Book;
import io.github.williamandradesantana.libraryapi.model.enums.Gender;
import io.github.williamandradesantana.libraryapi.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
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

    @GetMapping("/")
    public ResponseEntity<List<SearchBookDTO>> findAll(
            @RequestParam(value = "isbn", required = false) String isbn,
            @RequestParam(value = "author_name", required = false) String authorName,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "gender", required = false) Gender gender,
            @RequestParam(value = "year_of_publication", required = false) Integer yearOfPublication
    ) {
        var books = bookService
                .searchBySpecification(isbn, title, authorName, gender, yearOfPublication)
                .stream().map(bookMapper::toDTO).toList();
        return ResponseEntity.ok().body(books);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody BookRegisterDTO dto) {
        return bookService.get(UUID.fromString(id)).map(b -> {
            Book entity = bookMapper.toEntity(dto);

            b.setPublishDate(entity.getPublishDate());
            b.setTitle(entity.getTitle());
            b.setIsbn(entity.getIsbn());
            b.setPrice(entity.getPrice());
            b.setGender(entity.getGender());
            b.setAuthor(entity.getAuthor());

            bookService.update(b);
            return ResponseEntity.noContent().build();

        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
