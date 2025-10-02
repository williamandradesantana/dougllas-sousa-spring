package io.github.williamandradesantana.libraryapi.controller.mappers;

import io.github.williamandradesantana.libraryapi.controller.dto.BookRegisterDTO;
import io.github.williamandradesantana.libraryapi.model.Book;
import io.github.williamandradesantana.libraryapi.repositories.AuthorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @Autowired
    AuthorRepository authorRepository;

    @Mapping(target = "author",
            expression = "java(authorRepository.findById(dto.authorId()).orElse(null))")
    public abstract Book toEntity(BookRegisterDTO dto);
}
